package es.caib.projectebase.dir3caib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static javax.faces.annotation.FacesConfig.Version.JSF_2_3;

/**
 * Configuració de l'exemple de Dir3Caib. Accedeix al fitxer dir3caib/Dir3Caib.properties per llegir les propietats
 * de configuració de l'exemple.
 * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
 *
 * @author areus
 */
@ApplicationScoped
public class Configuracio {

    private static final Logger LOG = LoggerFactory.getLogger(Configuracio.class);

    private static final String BASE_URL_PROPERTY = "es.caib.projectebase.dir3caib.baseUrl";

    private String baseUrl;

    /**
     * Carrega el fitxer de properties notib/Notib.properties per inicialitzar la configuració.
     */
    @PostConstruct
    protected void init() {
        LOG.info("Carregant properties...");
        Properties properties = new Properties();
        try (InputStream is = this.getClass().getResourceAsStream("/dir3caib/Dir3Caib.properties")) {
            properties.load(is);

            LOG.info("Properties carregades: {}", properties);
        } catch (IOException io) {
            throw new RuntimeException("No s'han pogut llegir les propietats de configuració", io);
        }

        baseUrl = properties.getProperty(BASE_URL_PROPERTY);
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
