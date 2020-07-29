package es.caib.projectebase.back.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

/**
 * Bean per mantenir el locale de l'usuari.
 *
 * @author areus
 */
@Named
@SessionScoped
public class UserLocale implements Serializable {

    private static final long serialVersionUID = -3709390221710580769L;

    private static final Logger LOG = LoggerFactory.getLogger(UserLocale.class);

    @Inject
    private FacesContext context;

    /** Locale actual de l'usuari */
    private Locale current;

    public Locale getCurrent() {
        return current;
    }

    public void setCurrent(Locale current) {
        this.current = current;
    }

    // Mètodes

    /**
     * Inicialització del locale de l'usuari.
     */
    @PostConstruct
    private void init() {
        LOG.info("Inicialitzant locale de l'usuari");
        Application app = context.getApplication();
        current = app.getViewHandler().calculateLocale(context);
    }

    public void reload() {
        context.getPartialViewContext().getEvalScripts()
                .add("location.replace(location)");
    }
}
