package es.caib.projectebase.sistra2.integracio;

import es.caib.distribucio.ws.backofficeintegracio.BackofficeIntegracio;
import es.caib.distribucio.ws.backofficeintegracio.BackofficeIntegracioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Per instanciar el client del backoffice d'integració
 */
public class BackofficeIntegracioProducer {

    private static final Logger LOG = LoggerFactory.getLogger(BackofficeIntegracioProducer.class);

    private static final String ENDPOINT_PROPERTY = "es.caib.projectebase.sistra2.backofficeintegracio.endpoint";
    private static final String USERNAME_PROPERTY = "es.caib.projectebase.sistra2.backofficeintegracio.username";
    private static final String PASSWORD_PROPERTY = "es.caib.projectebase.sistra2.backofficeintegracio.password";

    /**
     * Instancia i configura el client de backoffice integració de distribució.
     * @param configuracio per accedir a les propietats de configuració.
     * @return
     */
    @Produces
    @ApplicationScoped
    public BackofficeIntegracio produceBackofficeIntegracio(Configuracio configuracio) {
        if (LOG.isInfoEnabled()) {
            LOG.info("produceBackofficeIntegracio");
        }

        URL wsdlLocation = this.getClass().getResource("/wsdl/backofficeIntegracio.wsdl");
        BackofficeIntegracio backofficeIntegracio
                = new BackofficeIntegracioService(wsdlLocation).getBackofficeIntegracioServicePort();

        BindingProvider bindingProvider = (BindingProvider) backofficeIntegracio;
        bindingProvider.getBinding().setHandlerChain(List.of(new LoggingHandler()));

        Map<String, Object> requestContext = bindingProvider.getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, configuracio.get(ENDPOINT_PROPERTY));
        requestContext.put(BindingProvider.USERNAME_PROPERTY, configuracio.get(USERNAME_PROPERTY));
        requestContext.put(BindingProvider.PASSWORD_PROPERTY, configuracio.get(PASSWORD_PROPERTY));

        return backofficeIntegracio;
    }
}
