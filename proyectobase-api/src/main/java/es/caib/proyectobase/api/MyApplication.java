package es.caib.proyectobase.api;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;


@ApplicationPath("/services")
public class MyApplication extends Application {

    public MyApplication(@Context ServletConfig servletConfig) {
        super();

        BeanConfig beanConfig = new BeanConfig();

        beanConfig.setVersion("1.0.0");
        beanConfig.setTitle("Proyecto Base API");
        beanConfig.setBasePath(servletConfig.getServletContext().getContextPath() + "/services");
        beanConfig.setResourcePackage("es.caib.proyectobase.api.resources");
        beanConfig.setScan(true);
        }
}