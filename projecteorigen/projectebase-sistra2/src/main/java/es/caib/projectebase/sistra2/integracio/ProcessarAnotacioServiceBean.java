package es.caib.projectebase.sistra2.integracio;

import es.caib.distribucio.backoffice.utils.arxiu.ArxiuResultat;
import es.caib.distribucio.backoffice.utils.arxiu.BackofficeArxiuUtils;
import es.caib.distribucio.backoffice.utils.arxiu.BackofficeArxiuUtilsImpl;
import es.caib.distribucio.backoffice.utils.arxiu.DistribucioArxiuError;
import es.caib.distribucio.backoffice.utils.sistra.formulario.Formulario;
import es.caib.distribucio.backoffice.utils.sistra.pago.Pago;
import es.caib.distribucio.core.api.dto.ExpedientEstatEnumDto;
import es.caib.distribucio.ws.backofficeintegracio.Annex;
import es.caib.distribucio.ws.backofficeintegracio.AnotacioRegistreEntrada;
import es.caib.distribucio.ws.backofficeintegracio.AnotacioRegistreId;
import es.caib.distribucio.ws.backofficeintegracio.BackofficeIntegracio;
import es.caib.distribucio.ws.backofficeintegracio.Consulta;
import es.caib.distribucio.ws.backofficeintegracio.Estat;
import es.caib.plugins.arxiu.api.Document;
import es.caib.plugins.arxiu.api.IArxiuPlugin;
import es.caib.projectebase.sistra2.converter.AnotacioInboxConverter;
import es.caib.projectebase.sistra2.persistence.AnotacioInbox;
import es.caib.projectebase.sistra2.repository.AnotacioInboxRepository;
import es.caib.projectebase.sistra2.repository.CannotLockException;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.xml.ws.WebServiceException;
import java.util.Date;
import java.util.List;

@Stateless
@Local(ProcessarAnotacioService.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Asynchronous
public class ProcessarAnotacioServiceBean implements ProcessarAnotacioService {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessarAnotacioServiceBean.class);

    @Inject
    private AnotacioInboxRepository anotacioInboxRepository;

    @Inject
    private AnotacioInboxConverter anotacioInboxConverter;

    @Inject
    private BackofficeIntegracio backofficeIntegracio;

    @Inject
    private IArxiuPlugin arxiuPlugin;

    @Override
    public void processarAnotacioPendent(Long id) {
        try {
            AnotacioInbox anotacioInbox = anotacioInboxRepository.lockById(id);
            if (anotacioInbox == null) {
                LOG.warn("No s'ha trobat l'AnotacioInbox amb id: {}", id);
                return;
            }

            AnotacioRegistreId arId  = anotacioInboxConverter.toAnotacioRegistre(anotacioInbox);
            AnotacioRegistreEntrada registreEntrada = backofficeIntegracio.consulta(arId);

            // si rebem correctament l'anotació de registre d'entrada enviam el canvi d'estat
            backofficeIntegracio.canviEstat(arId, Estat.REBUDA, null);

            // guaram la informació i canviam l'estat
            anotacioInbox.setContingut(JAXBUtil.marshallAnotacio(registreEntrada));
            anotacioInbox.setEstat(AnotacioInbox.Estat.REBUDA);

            // com que AnotacioInbox es managed no cal cridar explicitament al update.
            //anotacioInboxRepository.update(anotacioInbox);

        } catch (CannotLockException cle) {
            LOG.error("No s'ha pogut bloquejar l'AnotacioInbox amb id: {}", id);
        } catch (WebServiceException wse) {
            // TODO si es produeix un error de ws no feim res. caldria limitar reintents?
            LOG.error("Error cridant WS: {}", wse.getMessage());
            if (wse.getCause() != null) {
                LOG.error("Causa: {}", wse.getCause().getMessage());
            }
        }
    }

    private static final String CLASSIFICACIO = "000000";
    private static final String SERIE_DOCUMENTAL = "S0001";
    private static final List<String> ORGANS = List.of("A04013511");

    @Override
    public void processarAnotacioRebuda(Long id) {
        try {
            AnotacioInbox anotacioInbox = anotacioInboxRepository.lockById(id);
            if (anotacioInbox == null) {
                LOG.warn("No s'ha trobat l'AnotacioInbox amb id: {}", id);
                return;
            }

            AnotacioRegistreId arId  = anotacioInboxConverter.toAnotacioRegistre(anotacioInbox);
            AnotacioRegistreEntrada anotacio = JAXBUtil.unmarshallAnotacio(anotacioInbox.getContingut());

            // L'anotació s'ha processant anteriorment i s'ha creat arxiu, per tant no fa falta fer aquesta passa
            if (anotacioInbox.getExpedientArxiu() == null) {

                BackofficeArxiuUtils backofficeArxiuUtils = new BackofficeArxiuUtilsImpl(arxiuPlugin);
                backofficeArxiuUtils.setCarpeta(anotacio.getIdentificador());

                ArxiuResultat arxiuResultat = backofficeArxiuUtils.crearExpedientAmbAnotacioRegistre(
                        null,
                        anotacio.getIdentificador(),
                        null,
                        ORGANS,
                        new Date(),
                        CLASSIFICACIO,
                        ExpedientEstatEnumDto.OBERT,
                        SERIE_DOCUMENTAL,
                        anotacio);

                if (arxiuResultat.getErrorCodi() != DistribucioArxiuError.NO_ERROR) {
                    LOG.error("S'ha produit un error creant l'expedient a arxiu", arxiuResultat.getException());
                    // TODO caldria implementar reintents?
                    backofficeIntegracio.canviEstat(arId, Estat.ERROR, null);
                    anotacioInbox.setEstat(AnotacioInbox.Estat.ERROR);
                    return;
                }

                LOG.info("Creat l'expedient {}", arxiuResultat.getIdentificadorExpedient());
                anotacioInbox.setExpedientArxiu(arxiuResultat.getIdentificadorExpedient());
            }

            // processam els annexos
            for (Annex annex : anotacio.getAnnexos()) {
                String titol = annex.getTitol();
                if (("FORMULARIO".equals(titol)
                        || "PAGO".equals(titol))) {
                    // Recupera el contingut de l'annex
                    Document document = arxiuPlugin.documentDetalls(annex.getUuid(), null, true);
                    byte[] contingut = document.getContingut().getContingut();
                    LOG.info("  Document tècnic \"" + titol + "\". Dades:");
                    if ("FORMULARIO".equals(titol)) {
                        Formulario formulario = JAXBUtil.unmarshallFormulario(contingut);
                        LOG.info("  formulario: " + ToStringBuilder.reflectionToString(formulario));
                    } else if ("PAGO".equals(titol)) {
                        Pago pago = JAXBUtil.unmarshallPago(contingut);
                        LOG.info("  pago: " + ToStringBuilder.reflectionToString(pago));
                    }
                }

                // TODO: aquí caldria fer les comprovacions i si hi ha un error marcar l'estat ERROR o REBUTJADA
            }

            backofficeIntegracio.canviEstat(arId, Estat.PROCESSADA, null);
            // si no hi ha cap error marcam com a processada i ja no farem res més
            anotacioInbox.setEstat(AnotacioInbox.Estat.PROCESSADA);

        } catch (CannotLockException cle) {
            LOG.error("No s'ha pogut bloquejar el RegistreInbox amb id: {}", id);
        }
    }
}
