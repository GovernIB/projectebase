package es.caib.projectebase.commons.config;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Implementació del ConfigSource per obtenir propietats de configuració a partir del fitxer que s'ha de trobar
 * a la ruta indicada a la propietat de sistema indicada a l'estàndard.
 *
 * Aquesta classe es registra dins el fitxer META-INF/services/org.eclipse.microprofile.config.spi.ConfigSource
 */
public class PropertyFileConfigSource implements ConfigSource {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyFileConfigSource.class);

    private static final String PROJECTEBASE_PROPERTIES = "es.caib.projectebase.properties";

    private static final Map<String, String> PROPERTY_MAP;

    static {
        LOG.info("Carregant propietat de configuració: {}", PROJECTEBASE_PROPERTIES);

        String propertyFile = System.getProperty(PROJECTEBASE_PROPERTIES);
        if (propertyFile == null || propertyFile.isEmpty()) {
            throw new RuntimeException("Manca la propietat de sistema " + PROJECTEBASE_PROPERTIES);
        }
        LOG.info("Fitxer de propietats: {}", propertyFile);

        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(propertyFile)) {
            properties.load(inputStream);
        } catch (IOException exception) {
            throw new RuntimeException("Error llegit fitxer " + propertyFile, exception);
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("Propietats carregades: {}", properties.toString());
        }

        Map<String, String> map = new HashMap<>(properties.size());
        properties.stringPropertyNames().forEach(
                x -> map.put(x, properties.getProperty(x))
        );

        PROPERTY_MAP = Collections.unmodifiableMap(map);
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
