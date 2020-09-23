#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.controller;

import ${package}.service.facade.ProcedimentServiceFacade;
import ${package}.service.facade.UnitatOrganicaServiceFacade;
import ${package}.service.model.ProcedimentDTO;
import ${package}.service.model.UnitatOrganicaDTO;
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
import java.util.ResourceBundle;

/**
 * Controlador per l'edició de Procediments. El definim a l'scope de view perquè a nivell de request es
 * reconstruiria per cada petició AJAX, com ara amb els errors de validació. Amb view es manté mentre no es canvii
 * de vista.
 *
 * @author areus
 * @author anadal
 */
@Named
@ViewScoped
public class EditProcediment implements Serializable {

    private static final long serialVersionUID = -6369618058993094891L;

    private static final Logger LOG = LoggerFactory.getLogger(EditProcediment.class);

    @Inject
    private FacesContext context;

    @EJB
    ProcedimentServiceFacade procedimentService;

    @EJB
    UnitatOrganicaServiceFacade unitatOrganicaService;

    // PROPIETATS + GETTERS/SETTERS

    private UnitatOrganicaDTO unitatOrganica;

    private ProcedimentDTO procediment;

    /**
     * Obté la unitat orgànica a la que pertany el procediment que s'està editant
     */
    public UnitatOrganicaDTO getUnitatOrganica() {
        return unitatOrganica;
    }

    /**
     * Obté el procediment que s'està editant.
     */
    public ProcedimentDTO getProcediment() {
        return procediment;
    }

    /**
     * Indica si és una creació o una actualització segons s'hagi fixat o no l'id del procediment.
     *
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
        LOG.debug("init");
        procediment = new ProcedimentDTO();
        unitatOrganica = new UnitatOrganicaDTO();
    }

    // ACCIONS

    /**
     * Carrega el procediment i la unitat orgància per editar.
     */
    public void load() {
        LOG.debug("load");
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
     *
     * @return navegació cap al llistat d'unitats orgàniques.
     */
    public String saveOrUpdate() {
        LOG.debug("saveOrUpdate");
        // Obtenir el resource bundle d'etiquetes definit a faces-config.xml
        ResourceBundle labelsBundle = context.getApplication().getResourceBundle(context, "labels");

        // Feim una creació o una actualització.
        if (isCreate()) {
            procedimentService.create(procediment, unitatOrganica.getId());
            context.addMessage(null, new FacesMessage(labelsBundle.getString("msg.creaciocorrecta")));
        } else {
            procedimentService.update(procediment);
            context.addMessage(null, new FacesMessage(labelsBundle.getString("msg.actualitzaciocorrecta")));
        }

        // Els missatges no aguanten una redirecció ja que no es la mateixa petició
        // amb l'objecte flash podem assegurar que es guardin fins la visualització
        context.getExternalContext().getFlash().setKeepMessages(true);

        // Redireccionam cap al llistat de procediments, mantenint l'identificador de unitat orgànica
        return "/listProcediment?faces-redirect=true&includeViewParams=true";
    }
}