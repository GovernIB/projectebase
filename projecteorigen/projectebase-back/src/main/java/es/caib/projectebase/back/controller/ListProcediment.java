package es.caib.projectebase.back.controller;

import es.caib.projectebase.service.facade.ProcedimentServiceFacade;
import es.caib.projectebase.service.facade.UnitatOrganicaServiceFacade;
import es.caib.projectebase.service.model.Pagina;
import es.caib.projectebase.service.model.ProcedimentDTO;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
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
@Named("listProcediment")
@ViewScoped
public class ListProcediment extends AbstractController implements Serializable {

    private static final long serialVersionUID = -7992474170848445700L;

    private static final Logger LOG = LoggerFactory.getLogger(ListProcediment.class);

    @EJB
    UnitatOrganicaServiceFacade unitatOrganicaService;

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
    private UnitatOrganicaDTO unitatOrganica;

    public UnitatOrganicaDTO getUnitatOrganica() {
        return unitatOrganica;
    }

    /**
     * Inicialitzam el bean.
     */
    @PostConstruct
    public void init() {
        LOG.debug("init");
        unitatOrganica = new UnitatOrganicaDTO();
    }

    // ACCIONS

    /**
     * Carrega la unitat orgànica i els procediments.
     */
    public void load() throws IOException {
        LOG.debug("load");

        unitatOrganica = unitatOrganicaService.findById(unitatOrganica.getId())
                .orElseThrow(() -> new RuntimeException("id invàlid"));

        lazyModel = new LazyDataModel<>() {

            private static final long serialVersionUID = 1L;

            @Override
            public List<ProcedimentDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
                                             Map<String, Object> filters) {
                Pagina<ProcedimentDTO> pagina = procedimentService.findByUnitat(first, pageSize, unitatOrganica.getId());
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
        // Obtenir el resource bundle d'etiquetes definit a faces-config.xml
        ResourceBundle labelsBundle = getBundle("labels");

        procedimentService.delete(id);
        addGlobalMessage(labelsBundle.getString("msg.eliminaciocorrecta"));
    }
}
