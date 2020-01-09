#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.controller;

import ${package}.ejb.ProcedimentService;
import ${package}.ejb.UnitatOrganicaService;
import ${package}.jpa.Procediment;
import ${package}.jpa.UnitatOrganica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Controlador per obtenir la vista dels procediments d'una unitat orgànica. El definim a l'scope de view perquè
 * a nivell de request es reconstruiria per cada petició AJAX, com ara amb els errors de validació.
 * Amb view es manté mentre no es canvii de vista.
 *
 * @author areus
 */
@Named("listProcediment")
@ViewScoped
public class ListProcedimentController implements Serializable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @EJB
    UnitatOrganicaService unitatOrganicaService;

    @EJB
    ProcedimentService procedimentService;

    // PROPIETATS + GETTERS/SETTERS

    private UnitatOrganica unitatOrganica;
    private List<Procediment> procediments;

    /** Obté la unitat orgànica de la qual s'estàn llistat els procediments. */
    public UnitatOrganica getUnitatOrganica() {
        return unitatOrganica;
    }

    /** Obté la llista de procedmients associats a la unitat orgànica */
    public List<Procediment> getProcediments() {
        return procediments;
    }

    /**
     * Inicialitzam el bean.
     */
    @PostConstruct
    public void init() {
        log.info("init");
        unitatOrganica = new UnitatOrganica();
    }

    // ACCIONS

    /**
     * Carrega la unitat orgànica i els procediments.
     */
    public void load() {
        log.info("load");
        unitatOrganica = unitatOrganicaService.findById(unitatOrganica.getId());
        procediments = procedimentService.findAllByUnitatOrganica(unitatOrganica.getId());
    }
}
