package es.caib.projectebase.sistra2.facade.impl;

import es.caib.projectebase.sistra2.backoffice.api.AnotacioRegistreId;
import es.caib.projectebase.sistra2.facade.api.AnotacioFacadeService;
import es.caib.projectebase.sistra2.facade.exception.AnotacioIdInvalidException;
import es.caib.projectebase.sistra2.persistence.Anotacio;
import es.caib.projectebase.sistra2.persistence.EstatAnotacio;
import es.caib.projectebase.sistra2.service.AnotacioService;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.Queue;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Stateless
@Local(AnotacioFacadeService.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AnotacioFacadeServiceBean implements AnotacioFacadeService {

    @Inject
    private AnotacioService anotacioService;

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "java:app/jms/AnotacioQueue")
    private Queue queue;

    @Override
    public void rebreAnotacions(List<AnotacioRegistreId> anotacioIds) throws AnotacioIdInvalidException {
        checkAnotacionsId(anotacioIds);

        JMSProducer producer = jmsContext.createProducer();
        producer.setDeliveryDelay(2000);
        for (AnotacioRegistreId anotacioId : anotacioIds) {
            Map<String, Object> mapBody = Map.of(
                    "identificador", anotacioId.getIndetificador(),
                    "clauAccess", anotacioId.getClauAcces());
            producer.send(queue, mapBody);
        }
    }

    private void checkAnotacionsId(List<AnotacioRegistreId> ids) throws AnotacioIdInvalidException {
        for (AnotacioRegistreId id : ids) {
            if (isNullOrEmpty(id.getIndetificador()) || isNullOrEmpty(id.getClauAcces())) {
                throw new AnotacioIdInvalidException(id.getIndetificador(), id.getClauAcces());
            }
        }
    }

    private boolean isNullOrEmpty(String string) {
        return (string == null || string.isEmpty());
    }
}
