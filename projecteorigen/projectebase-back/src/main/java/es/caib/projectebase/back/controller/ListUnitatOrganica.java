package es.caib.projectebase.back.controller;

import es.caib.projectebase.back.utils.PFUtils;
import es.caib.projectebase.service.facade.UnitatOrganicaServiceFacade;
import es.caib.projectebase.service.model.Ordre;
import es.caib.projectebase.service.model.Pagina;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
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
public class ListUnitatOrganica implements Serializable {

    private static final long serialVersionUID = -6015369276336087696L;

    private static final Logger LOG = LoggerFactory.getLogger(ListUnitatOrganica.class);

    @Inject
    private FacesContext context;

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
            public List<UnitatOrganicaDTO> load(int first, int pageSize, List<SortMeta> multiSortMeta,
                                             Map<String, Object> filters) {

                List<Ordre> ordenacions = PFUtils.sortMetaToOrdre(multiSortMeta);

                Pagina<UnitatOrganicaDTO> pagina = unitatOrganicaService
                        .findFiltered(first, pageSize, filters, ordenacions);

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
        ResourceBundle labelsBundle = context.getApplication().getResourceBundle(context, "labels");

        unitatOrganicaService.delete(id);
        context.addMessage(null, new FacesMessage(labelsBundle.getString("msg.eliminaciocorrecta")));

        // No cal actualitzar el model perquè no aparegui el registre eliminat perquè primefaces cridarà
        // automàticament el load del lazyDataModel en refrescar el component del datatable.
    }
}
