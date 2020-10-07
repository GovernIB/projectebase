#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ResourceBundle;

public abstract class AbstractController {

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
        if (!context.isPostback() && context.isValidationFailed()) {
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
