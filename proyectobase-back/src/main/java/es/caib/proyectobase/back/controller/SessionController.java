package es.caib.proyectobase.back.controller;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 * Backing Bean para controlar la sesión del usuario
 * @author [u91310] Pedro Bauzá Mascaró 
 */
@SessionScoped
@Named("sessionController")
public class SessionController implements Serializable {

	public void logout() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
		externalContext.redirect("index.xhtml");
	}

}
