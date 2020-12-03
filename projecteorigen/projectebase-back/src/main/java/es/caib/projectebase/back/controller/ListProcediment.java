package es.caib.projectebase.back.controller;

import es.caib.projectebase.service.facade.ProcedimentServiceFacade;
import es.caib.projectebase.service.model.Pagina;
import es.caib.projectebase.service.model.ProcedimentDTO;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controlador per obtenir la vista dels procediments d'una unitat orgànica. El definim a l'scope de view perquè
 * a nivell de request es reconstruiria per cada petició AJAX, com ara amb els errors de validació.
 * Amb view es manté mentre no es canvii de vista.
 *
 * @author areus
 */
@Named
@ViewScoped
public class ListProcediment extends AbstractController implements Serializable {

    private static final long serialVersionUID = -7992474170848445700L;

    private static final Logger LOG = LoggerFactory.getLogger(ListProcediment.class);

    @EJB
    ProcedimentServiceFacade procedimentService;

    // PROPIETATS + GETTERS/SETTERS

    /**
     * Model de dades emprat pel compoment dataTable de primefaces.
     */
    private LazyDataModel<ProcedimentDTO> lazyModel;

    public LazyDataModel<ProcedimentDTO> getLazyModel() {
        return lazyModel;
    }

    /**
     * la unitat orgànica de la qual s'estàn llistat els procediments.
     */
    private UnitatOrganicaDTO unitat;

    public UnitatOrganicaDTO getUnitat() {
        return unitat;
    }
    
    public void setUnitat(UnitatOrganicaDTO unitat) {
        this.unitat = unitat;
    }

    // ACCIONS

    /**
     * Carrega la unitat orgànica i els procediments.
     */
    public void load() {
        LOG.debug("load");
        lazyModel = new LazyDataModel<>() {

            private static final long serialVersionUID = 1L;

            @Override
            public List<ProcedimentDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
                                             Map<String, FilterMeta> filterBy) {
                Pagina<ProcedimentDTO> pagina = procedimentService.findByUnitat(first, pageSize, unitat.getId());
                setRowCount((int) pagina.getTotal());
                return pagina.getItems();
            }
        };
    }

    /**
     * Esborra el procediment l'identificador indicat. El mètode retorna void perquè no cal navegació ja que
     * l'eliminació es realitza des de la pàgina de llistat, i quedam en aquesta pàgina.
     *
     * @param id identificador de del procediment.
     */
    public void delete(Long id) {
        LOG.debug("delete");
        
        procedimentService.delete(id);
        
        ResourceBundle labelsBundle = getBundle("labels");
        addGlobalMessage(labelsBundle.getString("msg.eliminaciocorrecta"));
    }
}
