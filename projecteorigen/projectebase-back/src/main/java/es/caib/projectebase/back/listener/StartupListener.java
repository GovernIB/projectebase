package es.caib.projectebase.back.listener;

import es.caib.projectebase.commons.utils.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Executat a l'inici del desplegament de l'aplicació web, així com en l'aturada.
 *
 * @author areus
 */
@WebListener
public class StartupListener implements ServletContextListener {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private Version version;

    /**
     * Executat quan s'inicialitza el contexte web. Treu un missatge amb la versió als logs.
     *
     * @param sce
     *            Informació de l'esdeveniment de context.
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("\nWebApp " + version.getProjectName() + ":"
                + "\n  + Version: " + version.getVersion()
                + "\n  + BuildTime: " + version.getBuildTime()
                + "\n  + Revision: " + version.getScmRevision());
    }

    /**
     * Executat quan es destrueix el contexte web.
     *
     * @param sce
     *            Informació de l'esdeveniment de context.
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Aturant aplicació ${project_name}");
    }
}
