package es.caib.projectebase.back.controller;

import es.caib.projectebase.jpa.Procediment;
import es.caib.projectebase.jpa.UnitatOrganica;
import es.caib.projectebase.service.ProcedimentService;
import es.caib.projectebase.service.UnitatOrganicaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Controlador per l'edició de Procediments. El definim a l'scope de view perquè a nivell de request es
 * reconstruiria per cada petició AJAX, com ara amb els errors de validació. Amb view es manté mentre no es canvii
 * de vista.
 *
 * @author areus
 */
@Named("editProcediment")
@ViewScoped
public class EditProcedimentController implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(EditProcedimentController.class);

    @Inject
    private FacesContext context;

    @Inject
    private Flash flash;

    @EJB
    ProcedimentService procedimentService;

    @EJB
    UnitatOrganicaService unitatOrganicaService;

    // PROPIETATS + GETTERS/SETTERS

    private UnitatOrganica unitatOrganica;

    private Procediment procediment;

    /**
     * Obté la unitat orgànica a la que pertany el procediment que s'està editant
     */
    public UnitatOrganica getUnitatOrganica() {
        return unitatOrganica;
    }

    /**
     * Obté el procediment que s'està editant.
     */
    public Procediment getProcediment() {
        return procediment;
    }

    /**
     * Indica si és una creació o una actualització segons s'hagi fixat o no l'id del procediment.
     * @return <code>true</code> si l'id és null, i per tant és una creació, <code>false</code> en cas contrari.
     */
    public boolean isCreate() {
        return procediment.getId() == null;
    }

    /**
     * Inicialitzam el bean amb les dades inicials.
     */
    @PostConstruct
    public void init() {
        LOG.info("init");
        procediment = new Procediment();
        unitatOrganica = new UnitatOrganica();
    }

    // ACCIONS

    /**
     * Carrega el procediment i la unitat orgància per editar.
     */
    public void load() {
        LOG.info("load");
        if (procediment.getId() != null) {
            procediment = procedimentService.findById(procediment.getId());
        }
        if (unitatOrganica.getId() != null) {
            unitatOrganica = unitatOrganicaService.findById(unitatOrganica.getId());
        }
    }

    /**
     * Crea o actualitza la unitat orgànica que s'està editant. Afegeix un missatge si s'ha fet amb èxit
     * i redirecciona cap a la pàgina de llistat.
     * @return navegació cap al llistat d'unitats orgàniques.
     */
    public String saveOrUpdate() {
        LOG.info("saveOrUpdate");
        if (isCreate()) {
            procedimentService.create(procediment, unitatOrganica.getId());
            context.addMessage(null, new FacesMessage("Creació correcte"));
        } else {
            procedimentService.update(procediment);
            context.addMessage(null, new FacesMessage("Actualització correcte"));
        }
        // Els missatges no aguanten una redirecció ja que no es la mateixa petició
        // amb l'objecte flash podem assegurar que es guardin fins la visualització
        flash.setKeepMessages(true);

        // Redireccionam cap al llistat de procediments, mantenint l'identificador de unitat orgànica
        return "/listProcediment?faces-redirect=true&includeViewParams=true";
    }
}
