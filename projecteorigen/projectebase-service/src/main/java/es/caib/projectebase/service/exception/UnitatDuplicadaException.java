package es.caib.projectebase.service.exception;

import java.util.Locale;

/**
 * Ja existeix una unitat orgànica amb el mateix codiDir3.
 *
 * @author areus
 */
public class UnitatDuplicadaException extends ServiceException {
    
    private static final long serialVersionUID = 1L;

    private final String codiDir3;

    public UnitatDuplicadaException(String codiDir3) {
        this.codiDir3 = codiDir3;
    }

    @Override
    public String getLocalizedMessage(Locale locale) {
        return translate(locale, "error.unitatDuplicada", codiDir3);
    }
}
