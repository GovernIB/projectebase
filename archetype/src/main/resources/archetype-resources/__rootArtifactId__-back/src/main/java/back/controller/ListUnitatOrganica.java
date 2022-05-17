#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.controller;

import ${package}.back.utils.PFUtils;
import ${package}.service.facade.UnitatOrganicaServiceFacade;
import ${package}.service.model.AtributUnitat;
import ${package}.service.model.Ordre;
import ${package}.service.model.Pagina;
import ${package}.service.model.UnitatOrganicaDTO;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controlador pels llistats d'Unitats Organiques. El definim a l'scope de view perquè a nivell de request es
 * reconstruiria per cada petició AJAX, com ara amb la paginació. Amb view es manté mentre no es canvii de vista.
 *
 * @author areus
 */
@Named
@ViewScoped
public class ListUnitatOrganica extends AbstractController implements Serializable {

    private static final long serialVersionUID = -6015369276336087696L;

    private static final Logger LOG = LoggerFactory.getLogger(ListUnitatOrganica.class);

    @EJB
    private UnitatOrganicaServiceFacade unitatOrganicaService;

    // PROPIETATS + GETTERS/SETTERS

    /**
     * Model de dades emprat pel compoment dataTable de primefaces.
     */
    private LazyDataModel<UnitatOrganicaDTO> lazyModel;

    public LazyDataModel<UnitatOrganicaDTO> getLazyModel() {
        return lazyModel;
    }

    /**
     * Inicialitzam el bean amb les dades inicials.
     */
    @PostConstruct
    public void init() {
        LOG.debug("init");

        lazyModel = new LazyDataModel<>() {

            private static final long serialVersionUID = 1L;

            /*
            Primefaces cridarà automàticament aquest mètode quan necessita actualitzar les dades del dataTable
            per qualsevol circumstància (filtres, ordenació, canvi de pàgina ...)
            */

            @Override
            public List<UnitatOrganicaDTO> load(int first, int pageSize, Map<String, SortMeta> sortBy,
                                                Map<String, FilterMeta> filterBy) {
                LOG.info("filterBy: {}", filterBy);

                // Dins JSF emprarem noms que coincideixin amb els valors de l'enumeració AtributUnitat
                Map<AtributUnitat, Object> filter = PFUtils.filterMetaToFilter(AtributUnitat.class, filterBy);
                List<Ordre<AtributUnitat>> ordenacions = PFUtils.sortMetaToOrdre(AtributUnitat.class, sortBy);

                Pagina<UnitatOrganicaDTO> pagina = unitatOrganicaService
                        .findFiltered(first, pageSize, filter, ordenacions);

                setRowCount((int) pagina.getTotal());
                return pagina.getItems();
            }
        };
    }

    // ACCIONS

    /**
     * Esborra l'unitat orgànica amb l'identificador indicat. El mètode retorna void perquè no cal navegació ja que
     * l'eliminació es realitza des de la pàgina de llistat, i quedam en aquesta pàgina.
     *
     * @param id identificador de l'unitat orgànica
     */
    public void delete(Long id) {
        LOG.debug("delete");
        // Obtenir el resource bundle d'etiquetes definit a faces-config.xml
        ResourceBundle labelsBundle = getBundle("labels");

        unitatOrganicaService.delete(id);
        addGlobalMessage(labelsBundle.getString("msg.eliminaciocorrecta"));

        // No cal actualitzar el model perquè no aparegui el registre eliminat perquè primefaces cridarà
        // automàticament el load del lazyDataModel en refrescar el component del datatable.
    }
}
