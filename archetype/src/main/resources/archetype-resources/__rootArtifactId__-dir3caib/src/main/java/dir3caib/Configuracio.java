#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dir3caib;

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
    @ConfigProperty(name="${package}.dir3caib.baseUrl")
    private String baseUrl;

    @Inject
    @ConfigProperty(name="${package}.dir3caib.usuari")
    private String usuari;

    @Inject
    @ConfigProperty(name="${package}.dir3caib.secret")
    private String secret;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getSecret() {
        return secret;
    }
}
