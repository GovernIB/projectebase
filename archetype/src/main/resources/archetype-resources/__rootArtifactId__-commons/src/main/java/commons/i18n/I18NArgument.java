#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.commons.i18n;

import java.io.Serializable;

/**
 * Representa un paràmetre per el formateig d'un missatge.
 *
 * @author anadal
 */
public interface I18NArgument extends Serializable {

    String getValue();
}
