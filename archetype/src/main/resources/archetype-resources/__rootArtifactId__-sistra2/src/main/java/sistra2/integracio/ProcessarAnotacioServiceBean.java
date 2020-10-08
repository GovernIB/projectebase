#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sistra2.integracio;

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
import ${package}.sistra2.converter.AnotacioInboxConverter;
import ${package}.sistra2.persistence.AnotacioInbox;
import ${package}.sistra2.repository.AnotacioInboxRepository;
import ${package}.sistra2.repository.CannotLockException;
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

/**
 * EJB que processa anotacions. El definim asíncron.
 */
@Stateless
@Local(ProcessarAnotacioService.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Asynchronous
public class ProcessarAnotacioServiceBean implements ProcessarAnotacioService {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessarAnotacioServiceBean.class);

    private static final String PREFIXEXPEDIENT_PROPERTY = "${package}.sistra2.prefixExpedient";
    private static final String SERIEDOCUMENTAL_PROPERTY = "${package}.sistra2.serieDocumental";
    private static final String ORGAN_PROPERTY = "${package}.sistra2.organ";

    @Inject
    private Configuracio configuracio;

    @Inject
    private AnotacioInboxRepository anotacioInboxRepository;

    @Inject
    private AnotacioInboxConverter anotacioInboxConverter;

    @Inject
    private BackofficeIntegracio backofficeIntegracio;

    @Inject
    private IArxiuPlugin arxiuPlugin;

    /**
     * Processa una anotació pendent. Intenta obtenir l'anotació del backoffice d'integració i si té èxit intenta
     * canviar l'estat a REBUDA.
     *
     * Tal com està implementat es faran infinits reintents si falla la consulta o si falla el canvi d'estat. Si falla
     * el canvi d'estat però, només és reintentarà aquest, no es repeteix la consulta.
     *
     * @param id identificador de l'anotació.
     */
    @Override
    public void processarAnotacioPendent(Long id) {
        try {
            // Bloquejam per evitar que mentre estam treballant un segon procés la modifiqui de forma concurrent.
            AnotacioInbox anotacioInbox = anotacioInboxRepository.lockById(id);
            if (anotacioInbox == null) {
                LOG.warn("No s'ha trobat l'AnotacioInbox amb id: {}", id);
                return;
            }

            AnotacioRegistreId arId  = anotacioInboxConverter.toAnotacioRegistre(anotacioInbox);

            // Es possible que ja haguem obtingut l'anotació i el que hagi fallat és el canvi d'estat per tant si ja
            // tenim el contingut no el tornam a obtenir.
            if (anotacioInbox.getContingut() == null) {
                AnotacioRegistreEntrada registreEntrada = backofficeIntegracio.consulta(arId);
                anotacioInbox.setContingut(JAXBUtil.marshallAnotacio(registreEntrada));
            }

            // en ja tenir el contingut, enviam el canvi d'estat a REBUDA i actualitzam l'estat.
            backofficeIntegracio.canviEstat(arId, Estat.REBUDA, null);
            anotacioInbox.setEstat(AnotacioInbox.Estat.REBUDA);

        } catch (CannotLockException cle) {
            LOG.error("No s'ha pogut bloquejar l'AnotacioInbox amb id: {}", id);
        } catch (WebServiceException wse) {
            LOG.error("Error cridant WS: {}", wse.getMessage());
            if (wse.getCause() != null) {
                LOG.error("Causa: {}", wse.getCause().getMessage());
            }
        }
    }

    /**
     * Processa una anotació rebuda. Intenta crear l'expedient a l'arxiu i si té èxit processa els annexos i intenta
     * canviar l'estat a PROCESSADA.
     *
     * Tal com està implementat es faran infinits reintents si falla la creació de l'arxiu amb un error no controlat
     * o si falla el processat dels annexos.
     *
     * Si falla el canvi d'estat però, només és reintentarà aquest, no es repeteix la creació de l'arxiu.
     * Si falla la creació de l'arxiu amb un error controlat, s'itenta marcar l'estat a ERROR.
     *
     * @param id identificador de l'anotació.
     */
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

            // Potser l'anotació s'ha processant anteriorment i s'ha creat arxiu, per tant no fa falta fer aquesta passa
            if (anotacioInbox.getExpedientArxiu() == null) {

                BackofficeArxiuUtils backofficeArxiuUtils = new BackofficeArxiuUtilsImpl(arxiuPlugin);
                backofficeArxiuUtils.setCarpeta(null);
                String nomExpedient = configuracio.get(PREFIXEXPEDIENT_PROPERTY) + anotacio.getIdentificador();

                ArxiuResultat arxiuResultat = backofficeArxiuUtils.crearExpedientAmbAnotacioRegistre(
                        null, // perquè crei un nou expedient
                        nomExpedient,
                        null,
                        List.of(configuracio.get(ORGAN_PROPERTY)),
                        new Date(),
                        anotacio.getProcedimentCodi(), // la classificació serà el codi de procediment
                        ExpedientEstatEnumDto.OBERT,
                        configuracio.get(SERIEDOCUMENTAL_PROPERTY),
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
                    LOG.info("  Document tècnic ${symbol_escape}"" + titol + "${symbol_escape}". Dades:");
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
        } catch (WebServiceException wse) {
            LOG.error("Error cridant WS: {}", wse.getMessage());
            if (wse.getCause() != null) {
                LOG.error("Causa: {}", wse.getCause().getMessage());
            }
        }
    }
}
