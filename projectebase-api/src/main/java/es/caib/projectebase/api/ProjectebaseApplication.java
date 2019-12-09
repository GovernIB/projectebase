package es.caib.projectebase.api;

import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configuració de l'aplicació JAX-RS. Es pot fer mitjançant aquesta subclasse de {@link Application} o
 * mitjançant la configuració a web.xml.
 */
@ApplicationPath("/services")
@Server(url="http://localhost:8080/projectebase/api")
public class ProjectebaseApplication extends Application {

    private static final Logger log = LoggerFactory.getLogger(ProjectebaseApplication.class);

    public ProjectebaseApplication() {
    }

    @PostConstruct
    private void init() {
        log.info("Iniciant API REST");
    }
}
