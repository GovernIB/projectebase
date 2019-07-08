package es.caib.projectebase.back.servlet;

import es.caib.projectebase.Version;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que inicialitza l'aplicació
 * TODO: és més apropiat emprar un ServletContextListener per fer aquesta tasca que no tenir un servlet sense mapping
 */
public class StartupServlet extends HttpServlet {

    @Inject
    private Version version;

    @Inject
    private Logger log;

    public void init() {
        log.info("Cargando la aplicación PROJECTEBASE versión "+ version.getVersion() +
                " generada en "+ version.getBuildTime());
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) {}

}