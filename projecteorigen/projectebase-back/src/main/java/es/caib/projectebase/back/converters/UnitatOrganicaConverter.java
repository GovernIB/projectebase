package es.caib.projectebase.back.converters;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import es.caib.projectebase.service.facade.UnitatOrganicaServiceFacade;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;

/**
 * Permet obtenir un bean a través del seu id passat com a paràmetre
 * automàticament.
 */
@FacesConverter(forClass = UnitatOrganicaDTO.class, managed = true)
public class UnitatOrganicaConverter implements Converter<UnitatOrganicaDTO> {

    @EJB
    UnitatOrganicaServiceFacade unitatOrganicaService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, UnitatOrganicaDTO unitat) {
        // Ha retornar un string buid perquè funcioni dins selects.
        if (unitat == null || unitat.getId() == null) {
            return "";
        }

        return unitat.getId().toString();
    }

    @Override
    public UnitatOrganicaDTO getAsObject(FacesContext context, UIComponent component, String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        try {
            return unitatOrganicaService.findById(Long.valueOf(id)).orElse(null);
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage("ID invàlid"), e);
        }
    }
}