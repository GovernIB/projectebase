#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.distribucio;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Configuració de l'exemple de Distribucio.
 * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
 *
 * @author areus
 */
@Named
@ApplicationScoped
public class Configuracio {

    @Inject
    @ConfigProperty(name = "${package}.int.distribucio.endpoint")
    private String endpoint;

    @Inject
    @ConfigProperty(name = "${package}.int.distribucio.usuari")
    private String usuari;

    @Inject
    @ConfigProperty(name = "${package}.int.distribucio.secret")
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
