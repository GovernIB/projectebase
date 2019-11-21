package es.caib.projectebase.back.listener;

import es.caib.projectebase.Version;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Executat a l'inici del desplegament de l'aplicació web, així com en l'aturada.
 */
@WebListener
public class StartupListener implements ServletContextListener {

    @Inject
    private Version version;

    @Inject
    private Logger log;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Aplicació PROJECTEBASE versión: "+ version.getVersion() +
                " generada en "+ version.getBuildTime());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Aturant aplicació PROJECTEBASE");
    }
}
