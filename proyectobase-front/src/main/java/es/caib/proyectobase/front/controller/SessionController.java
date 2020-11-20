package es.caib.proyectobase.front.controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Backing Bean para controlar la sesión del usuario
 * @author [u91310] Pedro Bauzá Mascaró 
 */
@SessionScoped
@ManagedBean(name="sessionController")
public class SessionController {

	public void logout() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
		externalContext.redirect("index.xhtml");
	}

}
