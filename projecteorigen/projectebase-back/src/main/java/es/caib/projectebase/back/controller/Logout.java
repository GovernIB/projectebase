package es.caib.projectebase.back.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Bean per implementar l'acció de logout.
 *
 * @author areus
 */
@Named
@RequestScoped
public class Logout {

    private static final Logger LOG = LoggerFactory.getLogger(Logout.class);

    @Inject
    private HttpServletRequest request;

    @Inject
    private FacesContext context;

    /**
     * Realitza un logout i redirecciona a la pàgina principal.
     *
     * @return navegació cap a la pàgina principal
     */
    public String logout() {
        LOG.info("logout");
        try {
            request.logout();
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        } catch (ServletException e) {
            LOG.error("Error durant el logout", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            // Atès que farem una redirecció fixam l'objecte flash perquè guardi el missatge fins la visualització
            context.getExternalContext().getFlash().setKeepMessages(true);
        }
        return "/index?faces-redirect=true";
    }
}
