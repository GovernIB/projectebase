package es.caib.projectebase.commons.config;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Implementació del ConfigSource per obtenir propietats de configuració a partir dels fitxer que s'han de trobar
 * a la ruta indicada a les propietats de sistema indicades a l'estàndard (Apartat 3.5.2)
 *
 * Aquesta classe es registra dins el fitxer META-INF/services/org.eclipse.microprofile.config.spi.ConfigSource
 */
public class PropertyFileConfigSource implements ConfigSource {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyFileConfigSource.class);

    private static final String[] FILE_NAME_PROPERTIES = {
            "es.caib.projectebase.properties",
            "es.caib.projectebase.system.properties"
    };

    private static final Map<String, String> PROPERTY_MAP;

    static {
        LOG.info("Carregant propietats de configuració.... ");

        Map<String, String> propertyMap = new HashMap<>();
        for (String fileNameProperty : FILE_NAME_PROPERTIES) {
            LOG.info("Comprovant propietat: {}", fileNameProperty);

            String propertyFile = System.getProperty(fileNameProperty);
            if (propertyFile == null || propertyFile.isEmpty()) {
                throw new RuntimeException("Manca la propietat de sistema " + fileNameProperty);
            }
            LOG.info("Fitxer de propietats: {}", propertyFile);

            Properties properties = new Properties();
            try (Reader reader = new FileReader(propertyFile, StandardCharsets.UTF_8)) {
                properties.load(reader);
            } catch (IOException exception) {
                throw new RuntimeException("Error llegint fitxer " + propertyFile, exception);
            }

            properties.stringPropertyNames().forEach(
                    x -> propertyMap.put(x, properties.getProperty(x))
            );
        }

        PROPERTY_MAP = Collections.unmodifiableMap(propertyMap);
    }

    public PropertyFileConfigSource() {
    }

    @Override
    public Set<String> getPropertyNames() {
        return PROPERTY_MAP.keySet();
    }

    @Override
    public int getOrdinal() {
        return ConfigSource.DEFAULT_ORDINAL;
    }

    @Override
    public Map<String, String> getProperties() {
        return PROPERTY_MAP;
    }

    @Override
    public String getValue(String key) {
        return PROPERTY_MAP.get(key);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
