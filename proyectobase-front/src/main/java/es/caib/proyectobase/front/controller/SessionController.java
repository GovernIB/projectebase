package es.caib.proyectobase.front.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Backing Bean para controlar la sesión del usuario
 * @author [u91310] Pedro Bauzá Mascaró 
 */
@SessionScoped
@Named(value="sessionController")
public class SessionController implements Serializable {

	public void logout() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
		externalContext.redirect("index.xhtml");
	}

}
