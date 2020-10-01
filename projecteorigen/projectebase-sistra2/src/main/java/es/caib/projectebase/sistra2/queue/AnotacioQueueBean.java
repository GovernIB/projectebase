package es.caib.projectebase.sistra2.queue;

import es.caib.projectebase.sistra2.service.AnotacioDuplicadaException;
import es.caib.projectebase.sistra2.service.AnotacioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Map;

@JMSDestinationDefinition(
        name="java:app/jms/AnotacioQueue",
        interfaceName = "javax.jms.Queue",
        destinationName = "AnotacioQueue"
)
@MessageDriven(
        name = "AnotacioQueueBean",
        activationConfig = {
                @ActivationConfigProperty(
                        propertyName = "destinationLookup",
                        propertyValue = "java:app/jms/AnotacioQueue"),
                @ActivationConfigProperty(
                        propertyName = "destinationType",
                        propertyValue = "javax.jms.Queue"),
        })
public class AnotacioQueueBean implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(AnotacioQueueBean.class);

    @Resource
    private MessageDrivenContext context;

    @Inject
    private AnotacioService anotacioService;

    @Override
    public void onMessage(Message message) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> mapBody = (Map<String, Object>) message.getBody(Map.class);

            String identificador = (String) mapBody.get("identificador");
            String clauAccess = (String) mapBody.get("clauAccess");

            if (LOG.isDebugEnabled()) {
                LOG.debug("onMessage: {}, ID :{}", message.getJMSMessageID(), identificador);
            }

            anotacioService.createAnotacio(identificador, clauAccess);

        } catch (AnotacioDuplicadaException adException) {
            LOG.error("L'anotació amb ID {} ja existeix a la base de dades", adException.getId());
            LOG.error("S'ignorarà aquest missatge. Caldria avisar a Distribució que s'ha rebut aquest ID per duplicat");
        } catch (JMSException e) {
            LOG.error("Error JMS processant missatge", e);
            context.setRollbackOnly();
        }
    }
}