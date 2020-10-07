#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.exception;

import java.util.Locale;

/**
 * La unitat té procediments associats i per tat no es pot esborrar
 *
 * @author areus
 */
public class UnitatTeProcedimentsException extends ServiceException {

    private static final long serialVersionUID = 1L;

    private final Long idUnitat;

    public UnitatTeProcedimentsException(Long idUnitat) {
        this.idUnitat = idUnitat;
    }

    @Override
    public String getLocalizedMessage(Locale locale) {
        return translate(locale, "error.unitatTeProcediments", idUnitat);
    }
}
