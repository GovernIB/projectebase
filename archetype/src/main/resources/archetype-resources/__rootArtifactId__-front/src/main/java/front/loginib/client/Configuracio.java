#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.front.loginib.client;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Configuració per la integració amb LoginIB.
 * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
 *
 * @author areus
 */
@ApplicationScoped
public class Configuracio {

	@Inject
    @ConfigProperty(name = "${package}.front.int.loginib.endpoint")
    private String endpoint;

    @Inject
    @ConfigProperty(name = "${package}.front.int.loginib.usuari")
    private String usuari;

    @Inject
    @ConfigProperty(name = "${package}.front.int.loginib.secret")
    private String secret;

    @Inject
    @ConfigProperty(name = "${package}.front.loginib.aplicacion")
    private String aplicacion;

    @Inject
    @ConfigProperty(name = "${package}.front.loginib.entidad")
    private String entidad;

    @Inject
    @ConfigProperty(name = "${package}.front.loginib.qaa")
    private int qaa;

    @Inject
    @ConfigProperty(name = "${package}.front.loginib.metodosAutenticacion")
    private String metodosAutenticacion;

    public String getEndpoint() {
        return endpoint;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getSecret() {
        return secret;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public String getEntidad() {
        return entidad;
    }

    public int getQaa() {
        return qaa;
    }

    public String getMetodosAutenticacion() {
        return metodosAutenticacion;
    }

    @Override
    public String toString() {
        return "Configuracio{" +
                "endpoint='" + endpoint + '${symbol_escape}'' +
                ", usuari='" + usuari + '${symbol_escape}'' +
                ", secret='" + secret + '${symbol_escape}'' +
                ", aplicacion='" + aplicacion + '${symbol_escape}'' +
                ", entidad='" + entidad + '${symbol_escape}'' +
                ", qaa=" + qaa +
                ", metodosAutenticacion='" + metodosAutenticacion + '${symbol_escape}'' +
                '}';
    }
}
