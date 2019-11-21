package es.caib.projectebase.api;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

@ApplicationPath("/services")
public class MyApplication extends Application {

    public MyApplication() {}

    @Context
    private ServletContext servletContext;

    @PostConstruct
    public void init() {
    }

}