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
 * Controlador de Unitats Organiques. El definim a l'scope de view perquè a nivell de request es reconstruiria
 * per cada petició AJAX. Amb view es manté mentre no es canvii de pàgina.
 *
 * @author areus
 */
@Named("unitatOrganicaController")
@ViewScoped
public class UnitatOrganicaController implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(UnitatOrganicaController.class);

    @Inject
    private FacesContext context;

    @EJB
    UnitatOrganicaService unitatOrganicaService;

    // PROPIETATS + GETTERS/SETTERS

    private UnitatOrganica current;
    private LazyDataModel<UnitatOrganica> lazyModel;

    /**
     * Obté la unitat orgànica que s'està editant
     */
    public UnitatOrganica getCurrent() {
        return current;
    }

    /**
     * Obté el model de dades per la taula d'unitats orgàniques
     */
    public LazyDataModel<UnitatOrganica> getLazyModel() {
        return lazyModel;
    }

    /**
     * Inicialitzam el bean amb les dades inicials.
     */
    @PostConstruct
    public void init() {
        LOG.info("init");
        current = new UnitatOrganica();
        lazyModel = new LazyDataModel<>() {
            @Override
            public List<UnitatOrganica> load(int first, int pageSize, String sortField, SortOrder sortOrder,
                                             Map<String, Object> filters) {
                setRowCount((int) unitatOrganicaService.countAll());
                //TODO implementar ordenació i filtres
                return unitatOrganicaService.findAllPaged(first, pageSize);
            }
        };
    }

    // ACCIONS

    /**
     * Carrega la unitat orgànica per editar.
     *
     * @param id identificador de l'unitat orgànica
     */
    public void loadCurent(Long id) {
        LOG.info("loadCurrent");
        current = unitatOrganicaService.findById(id);
    }

    /**
     * Crea o actualitza la unitat orgànica que s'està editant.
     */
    public void saveOrUpdate() {
        LOG.info("saveOrUpdate");
        if (current.getId() != null) {
            unitatOrganicaService.update(current);
            context.addMessage(null, new FacesMessage("Actualització correcte"));
        } else {
            unitatOrganicaService.create(current);
            context.addMessage(null, new FacesMessage("Creació correcte"));
        }
        current = new UnitatOrganica();
    }

    /**
     * Esborra l'unitat orgància amb l'identificador indicat.
     *
     * @param id identificador de l'unitat orgànica
     */
    public void delete(Long id) {
        LOG.info("delete");
        unitatOrganicaService.deleteById(id);
        context.addMessage(null, new FacesMessage("Registre borrat"));
    }
}
