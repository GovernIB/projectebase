package es.caib.projectebase.loginib;

import es.caib.loginib.rest.api.v1.RDatosAutenticacion;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Manté les dades d'autenticació a nivell de sessió.
 *
 * @author areus
 */
@Named("loginib")
@SessionScoped
public class LoginIBModel implements Serializable {

	private static final long serialVersionUID = 1L;

    private RDatosAutenticacion datos;

    protected void setDatos(RDatosAutenticacion datos) {
        this.datos = datos;
    }

    public RDatosAutenticacion getDatos() {
        return datos;
    }

    public boolean isLogged() {
        return datos != null;
    }
}
