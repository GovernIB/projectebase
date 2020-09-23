package es.caib.projectebase.service.exception;

import java.util.Locale;

/**
 * Ja existeix una procediment amb el mateix codiSia.
 *
 * @author areus
 */
public class ProcedimentDuplicatException extends ServiceException {

    private static final long serialVersionUID = 1L;

    private final String codiSia;

    public ProcedimentDuplicatException(String codiSia) {
        this.codiSia = codiSia;
    }

    @Override
    public String getLocalizedMessage(Locale locale) {
        return translate(locale, "error.procedimentDuplicat", codiSia);
    }
}
