#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.exception;

import java.util.Locale;

/**
 * Excepció per indicar que el recurs sol·licitat en una operació no s'ha trobat.
 *
 * @author areus
 */
public class RecursNoTrobatException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public RecursNoTrobatException() {
    }

    @Override
    public String getLocalizedMessage(Locale locale) {
        return translate(locale, "error.recursNoTrobat");
    }
}

