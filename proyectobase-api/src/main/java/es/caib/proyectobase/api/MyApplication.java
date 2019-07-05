package es.caib.proyectobase.api;

import io.swagger.jaxrs.config.BeanConfig;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;


@ApplicationPath("/services")
@ApplicationScoped
public class MyApplication extends Application {

    public MyApplication() {}

    @Context
    private ServletContext servletContext;

    @PostConstruct
    public void init() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setTitle("Proyecto Base API");
        beanConfig.setBasePath(servletContext.getContextPath() + "/services");
        beanConfig.setBasePath("proyectobase/api/services");
        beanConfig.setResourcePackage("es.caib.proyectobase.api.resources");
        beanConfig.setScan(true);
    }

}