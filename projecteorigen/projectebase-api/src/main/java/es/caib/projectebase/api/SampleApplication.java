package es.caib.projectebase.api;

import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configuració de l'aplicació JAX-RS. Es pot fer mitjançant aquesta subclasse de {@link Application} o
 * mitjançant la configuració a web.xml. Fer-ho amb una subclasse permet posar-hi anotacions de OpenApi.
 * Aquí fixam un únic servidor, amb el contextpath. Hi podríem posar diverses URL on es pot accedir al servei.
 *
 * @author areus
 */
@ApplicationPath("/services")
@Server(url = "/projectebase/api")
public class SampleApplication extends Application {

    private static final Logger LOG = LoggerFactory.getLogger(SampleApplication.class);

    /**
     * Les aplicacions JAX-RS necessiten un constructor buid.
     */
    public SampleApplication() {
    }

    /**
     * Podem introduir tasques a realitzar per la inicialització de l'API REST.
     */
    @PostConstruct
    private void init() {
        LOG.info("Iniciant API REST");
    }
}
