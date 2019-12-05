package es.caib.projectebase.back.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Bean per controlar la sessió d'usuari
 *
 * @author areus
 */
@Named("sessionController")
@SessionScoped
@SuppressWarnings("CdiInjectionPointsInspection")
public class SessionController implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(SessionController.class);

    @Inject
    private ExternalContext externalContext;

    @Inject
    private FacesContext context;

    // Dades de sessió de l'usuari

    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    // Mètodes

    @PostConstruct
    private void init() {
        log.info("Inicialitzant sessió");
        // Per defecte inialitzam el locale de l'usuari amb el locale que haurà autodectat el view d'acord amb
        // punt 2.5.2.1 de l'especificació
        language = context.getViewRoot().getLocale().getLanguage();
    }

    public String logout() {
        log.info("logout");
        externalContext.invalidateSession();
        return "index?faces-redirect=true";
    }

}
