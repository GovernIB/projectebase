package es.caib.projectebase.dir3caib;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


/**
 * Configuració de l'exemple de Dir3Caib.
 * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
 *
 * @author areus
 */
@ApplicationScoped
public class Configuracio {

    @Inject
    @ConfigProperty(name="es.caib.projectebase.int.dir3caib.endpoint")
    private String endpoint;

    @Inject
    @ConfigProperty(name="es.caib.projectebase.int.dir3caib.usuari")
    private String usuari;

    @Inject
    @ConfigProperty(name="es.caib.projectebase.int.dir3caib.secret")
    private String secret;

    public String getEndpoint() {
        return endpoint;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getSecret() {
        return secret;
    }
}
