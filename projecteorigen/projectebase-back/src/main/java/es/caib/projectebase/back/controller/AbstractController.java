package es.caib.projectebase.back.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.ResourceBundle;

public abstract class AbstractController {

    @Inject
    private FacesContext context;

    protected FacesContext getContext() {
        return context;
    }

    protected ResourceBundle getBundle(String bundleName) {
        return context.getApplication().getResourceBundle(context, bundleName);
    }

    protected void addGlobalMessage(String message) {
        context.addMessage(null, new FacesMessage(message));
    }

    protected void keepMessages() {
        context.getExternalContext().getFlash().setKeepMessages(true);
    }
}
