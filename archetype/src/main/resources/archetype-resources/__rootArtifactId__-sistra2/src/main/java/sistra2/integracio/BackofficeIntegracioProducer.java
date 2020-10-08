#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sistra2.integracio;

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

public class BackofficeIntegracioProducer {

    private static final Logger LOG = LoggerFactory.getLogger(BackofficeIntegracioProducer.class);

    private static final String ENDPOINT_PROPERTY = "${package}.sistra2.backofficeintegracio.endpoint";
    private static final String USERNAME_PROPERTY = "${package}.sistra2.backofficeintegracio.username";
    private static final String PASSWORD_PROPERTY = "${package}.sistra2.backofficeintegracio.password";

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
