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
import javax.ejb.Timer;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class IntegracioScheculerBean {

    private static final Logger LOG = LoggerFactory.getLogger(IntegracioScheculerBean.class);

    @Inject
    private AnotacioInboxRepository anotacioInboxRepository;

    @Inject
    private ProcessarAnotacioService processarAnotacioService;

    @Schedules({
        @Schedule(second = "0,30", minute = "*", hour = "*", persistent = false, info = "normal"),
    })
    public void processarAnotacionsPendents(Timer timer) {
        LOG.info("processarAnotacionsPendents {}", timer.getInfo());
        anotacioInboxRepository.findAllPendents().forEach(
                id -> processarAnotacioService.processarAnotacioPendent(id)
        );
    }

    @Schedules({
        @Schedule(second = "15,45", minute = "*", hour = "*", persistent = false, info = "normal"),
    })
    public void processarAnotacionsRebudes(Timer timer) {
        LOG.info("processarAnotacionsRebudes {}", timer.getInfo());
        anotacioInboxRepository.findAllRebudes().forEach(
                id -> processarAnotacioService.processarAnotacioRebuda(id)
        );
    }
}
