#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.controller;

import ${package}.back.model.UnitatModel;
import ${package}.service.facade.ProcedimentServiceFacade;
import ${package}.service.model.Pagina;
import ${package}.service.model.ProcedimentDTO;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
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

    @Inject
    private UnitatModel unitat;

    /**
     * Model de dades emprat pel compoment dataTable de primefaces.
     */
    private LazyDataModel<ProcedimentDTO> lazyModel;

    public LazyDataModel<ProcedimentDTO> getLazyModel() {
        return lazyModel;
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
            public List<ProcedimentDTO> load(int first, int pageSize, Map<String, SortMeta> sortBy,
                    Map<String, FilterMeta> filterBy) {
                Pagina<ProcedimentDTO> pagina =
                        procedimentService.findByUnitat(first, pageSize, unitat.getValue().getId());
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
