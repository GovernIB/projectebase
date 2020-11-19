package es.caib.projectebase.registre;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Configuració de l'exemple de registre.
 * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
 *
 * @author areus
 */
@Named
@ApplicationScoped
public class Configuracio {

    // PROPIETATS DE SISTEMES

    @Inject
    @ConfigProperty(name="es.caib.projectebase.int.registre.endpoint")
    private String endpoint;

    @Inject
    @ConfigProperty(name="es.caib.projectebase.int.registre.usuari")
    private String usuari;

    @Inject
    @ConfigProperty(name="es.caib.projectebase.int.registre.secret")
    private String secret;

    // PROPIETATS D'APLICACIÓ

    @Inject
    @ConfigProperty(name="es.caib.projectebase.registre.entitat")
    private String entitat;

    @Inject
    @ConfigProperty(name="es.caib.projectebase.registre.oficina")
    private String oficina;

    @Inject
    @ConfigProperty(name="es.caib.projectebase.registre.llibre")
    private String llibre;

    @Inject
    @ConfigProperty(name="es.caib.projectebase.registre.organismeDesti")
    private String organismeDesti;

    public String getEndpoint() {
        return endpoint;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getSecret() {
        return secret;
    }

    public String getEntitat() {
        return entitat;
    }

    public String getOficina() {
        return oficina;
    }

    public String getLlibre() {
        return llibre;
    }

    public String getOrganismeDesti() {
        return organismeDesti;
    }
}
