package es.caib.proyectobase.back.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.caib.proyectobase.Version;
import org.apache.log4j.Logger;

public class StartupServlet extends HttpServlet {

    /** Logger **/
    private static final Logger LOGGER = Logger.getLogger(StartupServlet.class);

    public void init() throws ServletException {
        LOGGER.info("Cargando la aplicación PROYECTOBASE versión "+ Version.VERSION+
                " generada en "+Version.BUILDTIME);
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException {}

}