package es.caib.projectebase.arxiu;

import es.caib.plugins.arxiu.api.IArxiuPlugin;
import es.caib.plugins.arxiu.caib.ArxiuPluginCaib;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author areus
 */
public class ArxiuProducer {
    
    private static final Logger LOG = LoggerFactory.getLogger(ArxiuProducer.class);
    
    @Produces
    @ApplicationScoped
    public IArxiuPlugin getArxiuPlugin() {
        LOG.info("init");
        
        LOG.info("Carregant properties...");
        Properties properties = new Properties();
        try (InputStream is = this.getClass().getResourceAsStream("/arxiu/Arxiu.properties")) {
            properties.load(is);
            LOG.info("Properties carregades");
        } catch (IOException io) {
            throw new RuntimeException("No s'han pogut llegir les propietats de configuració", io);
        }
        
        LOG.info("Instanciant plugin...");
        IArxiuPlugin plugin = new ArxiuPluginCaib("", properties);
        LOG.info("Plugin instanciat");
        
        return plugin;
    }
}
