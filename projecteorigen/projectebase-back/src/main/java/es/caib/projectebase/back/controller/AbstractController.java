package es.caib.projectebase.back.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ResourceBundle;

public abstract class AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractController.class);

    @Inject
    private FacesContext context;

    protected FacesContext getContext() {
        return context;
    }

    protected void sendBadRequest() throws IOException {
        context.getExternalContext().responseSendError(400, "Bad request");
        context.responseComplete();
    }

    public void badRequestOnValidationFailed() throws IOException {
        LOG.info("badRequestOnValidationFailed");
        if (!context.isPostback() && context.isValidationFailed()) {
            LOG.error("sendBadRequest");
            sendBadRequest();
        }
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
