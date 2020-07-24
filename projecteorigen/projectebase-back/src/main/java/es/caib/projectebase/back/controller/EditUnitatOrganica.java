package es.caib.projectebase.back.controller;

import es.caib.projectebase.service.facade.UnitatOrganicaServiceFacade;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * Controlador per l'edició d'Unitats Organiques. El definim a l'scope de view perquè a nivell
 * de request es reconstruiria per cada petició AJAX, com ara amb els errors de validació. Amb
 * view es manté mentre no es canvii de vista.
 *
 * @author areus
 */
@Named
@ViewScoped
public class EditUnitatOrganica implements Serializable {

    private static final long serialVersionUID = -4092311228270716321L;

    private static final Logger LOG = LoggerFactory.getLogger(EditUnitatOrganica.class);

    @Inject
    private FacesContext context;

    @Inject
    UnitatOrganicaServiceFacade unitatOrganicaService;

    // PROPIETATS + GETTERS/SETTERS

    private UnitatOrganicaDTO current;

    /**
     * Obté la unitat orgànica que s'està editant
     */
    public UnitatOrganicaDTO getCurrent() {
        return current;
    }

    /**
     * Indica si és una creació o una actualització segons s'hagi fixat o no l'id de la unitat
     * orgànica.
     *
     * @return <code>true</code> si l'id és null, i per tant és una creació, <code>false</code>
     * en cas contrari.
     */
    public boolean isCreate() {
        return current.getId() == null;
    }

    /**
     * Inicialitzam el bean amb les dades inicials.
     */
    @PostConstruct
    public void init() {
        LOG.debug("init");
        current = new UnitatOrganicaDTO();
    }

    // ACCIONS

    /**
     * Carrega la unitat orgànica per editar.
     */
    public void load() {
        LOG.debug("load");
        if (current.getId() != null) {
            current = unitatOrganicaService.findById(current.getId());
        }
    }

    /**
     * Crea o actualitza la unitat orgànica que s'està editant. Afegeix un missatge si s'ha fet
     * amb èxit i redirecciona cap a la pàgina de llistat.
     *
     * @return navegació cap al llistat d'unitats orgàniques.
     */
    public String saveOrUpdate() {
        LOG.debug("saveOrUpdate");
        // Obtenir el resource bundle d'etiquetes definit a faces-config.xml
        ResourceBundle labelsBundle = context.getApplication().getResourceBundle(context, "labels");

        // Feim una creació o una actualització.
        if (isCreate()) {
            unitatOrganicaService.create(current);
            context.addMessage(null, new FacesMessage(labelsBundle.getString("msg.creaciocorrecta")));
        } else {
            unitatOrganicaService.update(current);
            context.addMessage(null, new FacesMessage(labelsBundle.getString("msg.actualitzaciocorrecta")));
        }

        // Els missatges no aguanten una redirecció ja que no es la mateixa petició
        // amb l'objecte flash podem assegurar que es guardin fins la visualització
        context.getExternalContext().getFlash().setKeepMessages(true);

        // Redireccionam cap al llistat d'unitats orgàniques
        return "/listUnitatOrganica?faces-redirect=true";
    }
}
