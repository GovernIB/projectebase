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

/**
 * Per instanciar el client del backoffice d'integració
 */
public class BackofficeIntegracioProducer {

    private static final Logger LOG = LoggerFactory.getLogger(BackofficeIntegracioProducer.class);

    /**
     * Instancia i configura el client de backoffice integració de distribució.
     * @param configuracio per accedir a les propietats de configuració.
     * @return client per accedir al backoffice d'integració
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
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, configuracio.getEndpoint());
        requestContext.put(BindingProvider.USERNAME_PROPERTY, configuracio.getUsuari());
        requestContext.put(BindingProvider.PASSWORD_PROPERTY, configuracio.getSecret());

        return backofficeIntegracio;
    }
}
