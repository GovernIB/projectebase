#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.listener;

import ${package}.commons.utils.Version;
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

    private static final Logger LOG = LoggerFactory.getLogger(StartupListener.class);

    @Inject
    private Version version;

    /**
     * Executat quan s'inicialitza el contexte web. Treu un missatge amb la versió als logs.
     *
     * @param sce Informació de l'esdeveniment de context.
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("${symbol_escape}nWebApp " + version.getProjectName() + ":"
                + "${symbol_escape}n  + Version: " + version.getVersion()
                + "${symbol_escape}n  + BuildTime: " + version.getBuildTime()
                + "${symbol_escape}n  + Revision: " + version.getScmRevision());
    }

    /**
     * Executat quan es destrueix el contexte web.
     *
     * @param sce Informació de l'esdeveniment de context.
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("Aturant aplicació " + version.getProjectName());
    }
}
