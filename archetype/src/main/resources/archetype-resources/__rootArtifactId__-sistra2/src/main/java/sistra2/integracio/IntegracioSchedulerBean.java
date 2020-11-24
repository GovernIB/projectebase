#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sistra2.integracio;

import ${package}.sistra2.repository.AnotacioInboxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

/**
 * EJB per programar events períodics
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class IntegracioSchedulerBean {

    private static final Logger LOG = LoggerFactory.getLogger(IntegracioSchedulerBean.class);

    @Inject
    private AnotacioInboxRepository anotacioInboxRepository;

    @Inject
    private ProcessarAnotacioService processarAnotacioService;

    /**
     * Per cada anotació en estat pendent crida al mètode per processar-la.
     * S'executa cada minut, al segon 0.
     */
    @Schedules({
        @Schedule(second = "0", minute = "*", hour = "*", persistent = false),
    })
    public void processarAnotacionsPendents() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("processarAnotacionsPendents");
        }
        // el processarAnotacioService és asíncron, per tant això cridarà a la vegada per totes les anotacions
        // pendents. Si s'en acumulàssin moltes això podria ser un problema, i caldria crear una cosa JMS.
        anotacioInboxRepository.findAllPendents().forEach(
                id -> processarAnotacioService.processarAnotacioPendent(id)
        );
    }

    /**
     * Per cada anotació en estat rebuda crida al mètode per processar-la.
     * S'executa cada minut, al segon 30.
     */
    @Schedules({
        @Schedule(second = "30", minute = "*", hour = "*", persistent = false),
    })
    public void processarAnotacionsRebudes() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("processarAnotacionsRebudes");
        }
        // el processarAnotacioService és asíncron, per tant això cridarà a la vegada per totes les anotacions
        // rebudes. Si s'en acumulàssin moltes això podria ser un problema, i caldria crear una cosa JMS.
        anotacioInboxRepository.findAllRebudes().forEach(
                id -> processarAnotacioService.processarAnotacioRebuda(id)
        );
    }
}
