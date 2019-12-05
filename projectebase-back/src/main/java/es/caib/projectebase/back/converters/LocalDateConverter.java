package es.caib.projectebase.back.converters;

import org.primefaces.component.datepicker.DatePicker;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Necessari per poder emprat LocalDate amb el component datePicker
 */
@FacesConverter(forClass = LocalDate.class)
public class LocalDateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(component));
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || (value instanceof String && ((String) value).isEmpty())) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(component));
        return formatter.format((LocalDate) value);
    }

    private String extractPattern(UIComponent component) {
        if (component instanceof DatePicker) {
            DatePicker datePicker = (DatePicker) component;
            return datePicker.getPattern();
        }

        return null;
    }
}
