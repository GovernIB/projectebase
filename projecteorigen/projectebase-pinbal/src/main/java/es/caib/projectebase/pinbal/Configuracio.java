package es.caib.projectebase.pinbal;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Configuració de l'exemple de Pinbal.
 * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
 *
 * @author areus
 */
@Named
@ApplicationScoped
public class Configuracio {

    // Dades de connexió
    @Inject
    @ConfigProperty(name = "es.caib.projectebase.int.pinbal.endpoint")
    private String endpoint;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.int.pinbal.usuari")
    private String usuari;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.int.pinbal.secret")
    private String secret;

    // Dades de la petició

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.pinbal.organismeSolicitant")
    private String organismeSolicitant;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.pinbal.unitatTramitadora")
    private String unitatTramitadora;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.pinbal.codiProcediment")
    private String codiProcediment;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.pinbal.finalitat")
    private String finalitat;



    public String getEndpoint() {
        return endpoint;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getSecret() {
        return secret;
    }

    public String getOrganismeSolicitant() {
        return organismeSolicitant;
    }

    public String getUnitatTramitadora() {
        return unitatTramitadora;
    }

    public String getCodiProcediment() {
        return codiProcediment;
    }

    public String getFinalitat() {
        return finalitat;
    }
}
