package es.caib.projectebase.sistra2.schedule;

import es.caib.projectebase.sistra2.integracio.service.ActualitzarAnotacioService;
import es.caib.projectebase.sistra2.service.AnotacioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AnotacioSchedulerBean {

    private static final Logger LOG = LoggerFactory.getLogger(AnotacioSchedulerBean.class);

    @Inject
    private AnotacioService anotacioService;

    @Inject
    private ActualitzarAnotacioService actualitzarAnotacioService;

    @Schedules({
        @Schedule(second = "*/15", minute = "*", hour = "*", persistent = false, info = "normal"),
    })
    public void processarPendents(Timer timer) {
        if (LOG.isInfoEnabled()) {
            LOG.info("processarPendents. Timer: {}", timer.getInfo());
        }

        List<String> ids = anotacioService.findIdsPendents(10);
        for (String id : ids) {
            actualitzarAnotacioService.marcarAnotacioRebuda(id);
        }
    }

}
