#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.commons.rest;

/**
 * Constants emprades a l'API REST
 *
 * @author areus
 */
public interface RestConstants {

    /**
     * Propietat del request per emmagatzemar el locale actual de la petició.
     */
    String REQUEST_LOCALE = "${package}.commons.rest.requestLocale";

    /**
     * Paràmetre del context definit al web.xml amb la llista de locales soportats.
     */
    String SUPPORTED_LOCALES = "${package}.commons.rest.supportedLocales";
}
