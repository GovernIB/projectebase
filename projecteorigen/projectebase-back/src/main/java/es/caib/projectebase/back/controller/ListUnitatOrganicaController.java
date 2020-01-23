package es.caib.projectebase.back.controller;

import es.caib.projectebase.commons.i18n.I18NException;
import es.caib.projectebase.ejb.UnitatOrganicaService;
import es.caib.projectebase.persistence.UnitatOrganica;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
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

/**
 * Controlador pels llistats d'Unitats Organiques. El definim a l'scope de view perquè a nivell de request es
 * reconstruiria per cada petició AJAX, com ara amb la paginació. Amb view es manté mentre no es canvii de vista.
 *
 * @author areus
 */
@Named("listUnitatOrganica")
@ViewScoped
public class ListUnitatOrganicaController implements Serializable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private FacesContext context;

    @EJB
    private UnitatOrganicaService unitatOrganicaService;

    // PROPIETATS + GETTERS/SETTERS

    /**
     * Model de dades emprat pel compoment dataTable de primefaces.
     */
    private LazyDataModel<UnitatOrganica> lazyModel;

    public LazyDataModel<UnitatOrganica> getLazyModel() {
        return lazyModel;
    }

    /**
     * Inicialitzam el bean amb les dades inicials.
     */
    @PostConstruct
    public void init() {
        log.debug("init");

        lazyModel = new LazyDataModel<>() {
            /*
            Primefaces cridarà automàticament aquest mètode quan necessita actualitzar les dades del dataTable
            per qualsevol circumstància (filtres, ordenació, canvi de pàgina ...)
            */
            @Override
            public List<UnitatOrganica> load(int first, int pageSize, String sortField, SortOrder sortOrder,
                                             Map<String, Object> filters) {
                try {
                    // Es necessari indicar el nombre de registres cada vegada que es carrega el model per si ha variat
                    setRowCount((int) unitatOrganicaService.countAll());
                    //TODO implementar ordenació i filtres
                    return unitatOrganicaService.selectAll(first, pageSize);
                } catch (I18NException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    // ACCIONS

    /**
     * Esborra l'unitat orgànica amb l'identificador indicat.
     *
     * @param id identificador de l'unitat orgànica
     */
    public void delete(Long id) {
        log.debug("delete");
        unitatOrganicaService.deleteById(id);
        context.addMessage(null, new FacesMessage("Registre borrat", ""));
    }
}
