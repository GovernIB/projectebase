#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.controller;

import ${package}.jpa.UnitatOrganica;
import ${package}.service.UnitatOrganicaService;
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

    private static final Logger LOG = LoggerFactory.getLogger(ListUnitatOrganicaController.class);

    @Inject
    private FacesContext context;

    @EJB
    private UnitatOrganicaService unitatOrganicaService;

    // PROPIETATS + GETTERS/SETTERS

    private PaginationHelper<UnitatOrganica> pagination;

    private String cerca;

    private LazyDataModel<UnitatOrganica> lazyModel;

    /**
     * Obté el unitats de dades per la taula d'unitats orgàniques
     */
    public LazyDataModel<UnitatOrganica> getLazyModel() {
        return lazyModel;
    }

    public PaginationHelper<UnitatOrganica> getPagination() {
        return pagination;
    }

    public String getCerca() {
        return cerca;
    }

    public void setCerca(String cerca) {
        this.cerca = cerca;
    }

    /**
     * Inicialitzam el bean amb les dades inicials.
     */
    @PostConstruct
    public void init() {
        LOG.info("init");
        lazyModel = new LazyDataModel<>() {
            @Override
            public List<UnitatOrganica> load(int first, int pageSize, String sortField, SortOrder sortOrder,
                                             Map<String, Object> filters) {
                setRowCount((int) unitatOrganicaService.countAll());
                //TODO implementar ordenació i filtres
                return unitatOrganicaService.findAllPaged(first, pageSize);
            }
        };
        pagination = new PaginationHelper<>() {
            @Override
            protected void updateModel() {
                setCount((int) unitatOrganicaService.countAll());
                //TODO implementar ordenació i filtres
                setModel(unitatOrganicaService.findAllPaged(this.getPageFirstItem(), this.getPageSize()));
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
        LOG.info("delete");
        unitatOrganicaService.deleteById(id);
        pagination.updateModel();
        context.addMessage(null, new FacesMessage("Registre borrat"));
    }
}
