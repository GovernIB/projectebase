package es.caib.projectebase.back.controller;

import es.caib.projectebase.service.facade.ProcedimentServiceFacade;
import es.caib.projectebase.service.facade.UnitatOrganicaServiceFacade;
import es.caib.projectebase.service.model.ProcedimentDTO;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;
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
public class ListProcediment implements Serializable {

    private static final long serialVersionUID = -7992474170848445700L;

    private static final Logger LOG = LoggerFactory.getLogger(ListProcediment.class);

    @Inject
    private FacesContext context;

    @EJB
    UnitatOrganicaServiceFacade unitatOrganicaService;

    @EJB
    ProcedimentServiceFacade procedimentService;

    // PROPIETATS + GETTERS/SETTERS

    private UnitatOrganicaDTO unitatOrganica;
    private List<ProcedimentDTO> procediments;

    /**
     * Obté la unitat orgànica de la qual s'estàn llistat els procediments.
     */
    public UnitatOrganicaDTO getUnitatOrganica() {
        return unitatOrganica;
    }

    /**
     * Obté la llista de procedmients associats a la unitat orgànica
     */
    public List<ProcedimentDTO> getProcediments() {
        return procediments;
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
    public void load() {
        LOG.debug("load");
        unitatOrganica = unitatOrganicaService.findById(unitatOrganica.getId());
        // TODO, paginació
        procediments = procedimentService.findByUnitat(0, 10, unitatOrganica.getId());
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
        ResourceBundle labelsBundle = context.getApplication().getResourceBundle(context, "labels");

        procedimentService.delete(id);
        context.addMessage(null, new FacesMessage(labelsBundle.getString("msg.eliminaciocorrecta")));

        // Actualitza el model de dades perquè desapareixi del llistat.
        // TODO, paginació
        procediments = procedimentService.findByUnitat(0, 10, unitatOrganica.getId());
    }
}
