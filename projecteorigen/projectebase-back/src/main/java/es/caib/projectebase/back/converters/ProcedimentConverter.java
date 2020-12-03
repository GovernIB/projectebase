package es.caib.projectebase.back.converters;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import es.caib.projectebase.service.facade.ProcedimentServiceFacade;
import es.caib.projectebase.service.model.ProcedimentDTO;

/**
 * Permet obtenir un bean a través del seu id passat com a paràmetre
 * automàticament.
 */
@FacesConverter(forClass = ProcedimentDTO.class, managed = true)
public class ProcedimentConverter implements Converter<ProcedimentDTO> {

    @EJB
    ProcedimentServiceFacade procedimentService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, ProcedimentDTO procediment) {
        // Ha retornar un string buid perquè funcioni dins selects.
        if (procediment == null || procediment.getId() == null) {
            return "";
        }

        return procediment.getId().toString();
    }

    @Override
    public ProcedimentDTO getAsObject(FacesContext context, UIComponent component, String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        try {
            return procedimentService.findById(Long.valueOf(id)).orElse(null);
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage("ID invàlid"), e);
        }
    }
}