package es.caib.projectebase.back.controller;

import es.caib.projectebase.commons.i18n.I18NException;
import es.caib.projectebase.commons.utils.Configuration;
import es.caib.projectebase.ejb.UnitatOrganicaService;
import es.caib.projectebase.ejb.utils.I18NTranslatorEjb;
import es.caib.projectebase.jpa.UnitatOrganica;

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
import java.util.Locale;

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

    /** Objecte que manté l'estat i les dades paginades */
    private PaginationHelper<UnitatOrganica> pagination;

    /** Cadena de cerca */
    private String cerca;

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
        log.debug("init");
        /*
        Crea un objecte paginació implementant el mètode per actualitzar el model perquè agafi les dades
         filtrades i paginades.
         */
        pagination = new PaginationHelper<>() {
            @Override
            public void updateModel() {
                setCount((int) unitatOrganicaService.countFilter(cerca));
                //TODO implementar ordenació
                try {
                    setModel(unitatOrganicaService.selectAll(cerca, this.getPageFirstItem(), this.getPageSize()));
                } catch (I18NException e) {
                    String msg = I18NTranslatorEjb.translate(e, new Locale(Configuration.getDefaultLanguage()));
                    log.error("Error en updateModel() => " + msg);
                    throw new RuntimeException(msg);
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
        // Actualitza les dades paginades
        pagination.updateModel();
        context.addMessage(null, new FacesMessage("Registre borrat"));
    }
}
