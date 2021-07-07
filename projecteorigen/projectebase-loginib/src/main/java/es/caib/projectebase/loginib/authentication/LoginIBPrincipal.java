package es.caib.projectebase.loginib.authentication;

import es.caib.loginib.rest.api.v1.RDatosAutenticacion;

import java.security.Principal;
import java.util.Objects;

public class LoginIBPrincipal implements Principal {

    private final RDatosAutenticacion datosAutenticacion;

    public LoginIBPrincipal(RDatosAutenticacion datosAutenticacion) {
        Objects.requireNonNull(datosAutenticacion, "datosAutenticacion és null");
        this.datosAutenticacion = datosAutenticacion;
    }

    @Override
    public String getName() {
        return datosAutenticacion.getNif();
    }
}
