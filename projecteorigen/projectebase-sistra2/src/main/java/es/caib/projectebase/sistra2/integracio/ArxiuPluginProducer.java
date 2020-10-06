package es.caib.projectebase.sistra2.integracio;

import es.caib.plugins.arxiu.api.IArxiuPlugin;
import es.caib.plugins.arxiu.caib.ArxiuPluginCaib;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ArxiuPluginProducer {
    
    private static final Logger LOG = LoggerFactory.getLogger(ArxiuPluginProducer.class);
    
    /**
     * Instancia el plugin d'arxiu. El marcam amb @Produces per poder injectar el plugin d'arxiu
     * dins qualsevol controlador. El marcam com a @ApplicationScoped per garantir que només s'en
     * instancia un.
     * 
     * @return instància del plugin d'arxiu.
     */
    @Produces
    @ApplicationScoped
    public IArxiuPlugin getArxiuPlugin() {
        LOG.info("getArxiuPlugin");
        
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