#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.commons.i18n;

/**
 * Representa un paràmetre per el formateig d'un missatge.
 *
 * @author anadal
 */
public interface I18NArgument {

    String getValue();
}
