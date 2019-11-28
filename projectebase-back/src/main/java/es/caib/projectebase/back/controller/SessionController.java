package es.caib.projectebase.back.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Bean per controlar la sessió d'usuari
 *
 * @author areus
 */
@SessionScoped
@Named("sessionController")
@SuppressWarnings("CdiInjectionPointsInspection")
public class SessionController implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(SessionController.class);

    @Inject
    private ExternalContext externalContext;

    public String logout() {
        log.info("logout");
        externalContext.invalidateSession();
        return "index?faces-redirect=true";
    }

}
