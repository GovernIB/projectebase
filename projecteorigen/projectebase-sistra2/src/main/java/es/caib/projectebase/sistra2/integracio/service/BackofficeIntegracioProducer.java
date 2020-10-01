package es.caib.projectebase.sistra2.integracio.service;

import es.caib.projectebase.sistra2.integracio.api.BackofficeIntegracio;
import es.caib.projectebase.sistra2.integracio.api.BackofficeIntegracioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.xml.ws.BindingProvider;
import java.util.Map;

public class BackofficeIntegracioProducer {

    private static final Logger LOG = LoggerFactory.getLogger(BackofficeIntegracioProducer.class);

    @Produces
    public BackofficeIntegracio produceBackofficeIntegracio() {
        if (LOG.isInfoEnabled()) {
            LOG.info("produceBackofficeIntegracio");
        }

        BackofficeIntegracio backofficeIntegracio
                = new BackofficeIntegracioService().getBackofficeIntegracioServicePort();
        BindingProvider bindingProvider = (BindingProvider) backofficeIntegracio;
        Map<String, Object> requestContext = bindingProvider.getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "https://dev.caib.es/distribucio/ws/backofficeIntegracio");
        return backofficeIntegracio;
    }
}
