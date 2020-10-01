package es.caib.projectebase.sistra2.integracio.service;

import es.caib.projectebase.sistra2.integracio.api.AnotacioRegistreId;
import es.caib.projectebase.sistra2.integracio.api.BackofficeIntegracio;
import es.caib.projectebase.sistra2.integracio.api.Estat;
import es.caib.projectebase.sistra2.persistence.Anotacio;
import es.caib.projectebase.sistra2.persistence.EstatAnotacio;
import es.caib.projectebase.sistra2.service.AnotacioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.xml.ws.WebServiceException;

@Stateless
@Local(ActualitzarAnotacioService.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Asynchronous
public class ActualitzarAnotacioServiceBean implements ActualitzarAnotacioService {

    private static final Logger LOG = LoggerFactory.getLogger(ActualitzarAnotacioService.class);

    @Inject
    private AnotacioService anotacioService;

    @Inject
    private BackofficeIntegracio backofficeIntegracio;

    @Override
    public void marcarAnotacioRebuda(String id) {
        if (LOG.isInfoEnabled()) {
            LOG.info("marcarAnotacioRebuda: {}", id);
        }

        Anotacio anotacio = anotacioService.lockById(id);
        AnotacioRegistreId anotacioRegistreId = new AnotacioRegistreId();
        anotacioRegistreId.setIndetificador(anotacio.getId());
        anotacioRegistreId.setClauAcces(anotacio.getClau());

        try {
            backofficeIntegracio.canviEstat(anotacioRegistreId, Estat.REBUDA, null);
            anotacio.setEstat(EstatAnotacio.REBUDA);
        } catch (WebServiceException wse) {
            LOG.error("Error canviant estat: {}", wse.getMessage());
            if (wse.getCause() != null) {
                LOG.error("Causa: {}", wse.getCause().getMessage());
            }
        }
    }
}
