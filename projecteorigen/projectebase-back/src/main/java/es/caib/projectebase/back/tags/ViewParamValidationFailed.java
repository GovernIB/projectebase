package es.caib.projectebase.back.tags;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ComponentSystemEventListener;
import javax.faces.event.PostValidateEvent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;
import java.io.IOException;

/**
 * Tag d'utilitat per permetre que si a una vista no es rep el paràmetre indicat faci una redirecció.
 * Està registrada al fitxer /WEB-INF/back.taglib.xml.
 *
 * Veure exemple d'utilització a: editUnitatOrganica.xhtml
 *
 * @author areus
 */
public class ViewParamValidationFailed extends TagHandler implements ComponentSystemEventListener {

    private final String redirect;

    public ViewParamValidationFailed(TagConfig config) {        
        super(config);
        redirect = getRequiredAttribute("redirect").getValue();
    }

    /**
     * Ens registram a l'event PostValidateEvent del component pare.
     */
    @Override
    public void apply(FaceletContext context, UIComponent parent) {
        if (parent instanceof UIViewParameter
                && !context.getFacesContext().isPostback()) {
            parent.subscribeToEvent(PostValidateEvent.class, this);
        }
    }

    /**
     * Si la validació ha fallat redireccionam a l'adreça indicada.
     * Podríem optar també per fer un sendError 400 BadRequest.
     */
    @Override
    public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
        UIComponent parent = event.getComponent();
        parent.unsubscribeFromEvent(PostValidateEvent.class, this);
        FacesContext context = event.getFacesContext();
        if (context.isValidationFailed()) {
            try {
                ExternalContext ec = context.getExternalContext();
                ec.redirect(ec.getRequestContextPath() + redirect);
            } catch (IOException e) {
                throw new AbortProcessingException(e);
            }
        }
    }
}
