package es.caib.projectebase.back.model;

import es.caib.projectebase.service.facade.ProcedimentServiceFacade;
import es.caib.projectebase.service.model.ProcedimentDTO;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class ProcedimentModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ProcedimentServiceFacade procedimentService;

    private ProcedimentDTO value = new ProcedimentDTO();

    public ProcedimentDTO getValue() {
        return value;
    }

    public void setValue(ProcedimentDTO value) {
        this.value = value;
    }

    public void load() {
        if (value.getId() == null) {
            throw new IllegalArgumentException("id is null");
        }
        value = procedimentService.findById(value.getId()).orElseThrow();
    }
}
