package es.caib.projectebase.arxiu;

import es.caib.plugins.arxiu.api.ContingutArxiu;
import es.caib.plugins.arxiu.api.Expedient;
import es.caib.plugins.arxiu.api.ExpedientEstat;
import es.caib.plugins.arxiu.api.ExpedientMetadades;
import es.caib.plugins.arxiu.api.IArxiuPlugin;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Antoni
 */
@Named
@SessionScoped
public class ExpedientController implements Serializable {
    
    private static final Logger LOG = LoggerFactory.getLogger(ExpedientController.class);
    
    @Inject
    private IArxiuPlugin plugin;
    
    @Inject
    private ExpedientModel model;
    
    private final List<ContingutArxiu> expedientsCreats = new ArrayList<>();

    public List<ContingutArxiu> getExpedientsCreats() {
        return expedientsCreats;
    }
    
    public void crearExpedient() {
        LOG.info("createExpedient");
        
        Expedient expedient = new Expedient();
        expedient.setNom(model.getName());
        
        LOG.info("Nom: " + expedient.getNom());
        
        final ExpedientMetadades metadades = new ExpedientMetadades();
        metadades.setOrgans(List.of("A04013511"));
        metadades.setDataObertura(new Date());
        metadades.setClassificacio("organo1_PRO_123456789");
        metadades.setEstat(ExpedientEstat.OBERT);
        metadades.setInteressats(List.of("12345678Z", "00000000T"));
        metadades.setSerieDocumental("S0001");
        
        expedient.setMetadades(metadades);
        
        ContingutArxiu expedientCreat = plugin.expedientCrear(expedient);
        expedientsCreats.add(expedientCreat);
        
        LOG.info("Expedient creat: " + expedientCreat.getIdentificador());
    }
    
    public void borrarExpedient(String identificador) {
        
        Optional<ContingutArxiu> optional = expedientsCreats.stream()
                .filter(expedient -> expedient.getIdentificador().equals(identificador))
                .findAny();
        
        if (optional.isPresent()) {
            LOG.info("Esborrant " + identificador);
            
            plugin.expedientEsborrar(identificador);
            
            expedientsCreats.remove(optional.get());
        }
    }
}
