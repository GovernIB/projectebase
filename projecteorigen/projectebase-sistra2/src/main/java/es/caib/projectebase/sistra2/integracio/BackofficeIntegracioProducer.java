package es.caib.projectebase.sistra2.integracio;

import es.caib.distribucio.ws.backofficeintegracio.BackofficeIntegracio;
import es.caib.distribucio.ws.backofficeintegracio.BackofficeIntegracioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.Map;

public class BackofficeIntegracioProducer {

    private static final Logger LOG = LoggerFactory.getLogger(BackofficeIntegracioProducer.class);

    @Produces
    @ApplicationScoped
    public BackofficeIntegracio produceBackofficeIntegracio() {
        if (LOG.isInfoEnabled()) {
            LOG.info("produceBackofficeIntegracio");
        }

        URL wsdlLocation = this.getClass().getResource("/wsdl/backofficeIntegracio.wsdl");
        BackofficeIntegracio backofficeIntegracio
                = new BackofficeIntegracioService(wsdlLocation).getBackofficeIntegracioServicePort();

        BindingProvider bindingProvider = (BindingProvider) backofficeIntegracio;
        Map<String, Object> requestContext = bindingProvider.getRequestContext();

        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://localhost:8888/distribucio/ws/backofficeIntegracio");

        return backofficeIntegracio;
    }
}
