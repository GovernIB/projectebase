package es.caib.projectebase.back.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Bean per controlar la sessió d'usuari. S'ha de mantenir dins l'scope de sessió, i per tant cal definir-ho
 * com a serialitzable. Anar en compte a mantenir referències a objectes no serialitzables.
 *
 * @author areus
 */
@Named("sessionController")
@SessionScoped
public class SessionController implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(SessionController.class);

    @Inject
    private Flash flash;

    @Inject
    private FacesContext context;

    // Dades de sessió de l'usuari

    private String language;

    /**
     * Obté l'idioma de l'usuari
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Fixa l'idioma de l'usuari
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    // Mètodes

    /**
     * Per defecte inialitzam el locale de l'usuari amb el locale que haurà autodectat el view d'acord amb
     * punt 2.5.2.1 de l'especificació
     */
    @PostConstruct
    private void init() {
        LOG.debug("Inicialitzant sessió");
        language = context.getViewRoot().getLocale().getLanguage();
    }

    /**
     * Invalida la sessió d'usuari i redirecciona a la pàgina principal.
     *
     * @return navegació cap a la pàgina principal
     */
    public String logout() {
        LOG.debug("logout");
        try {
            ((HttpServletRequest) context.getExternalContext().getRequest()).logout();
        } catch (ServletException e) {
            LOG.error("Error durant el logout", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
            flash.setKeepMessages(true);
        }
        return "/index?faces-redirect=true";
    }

}
