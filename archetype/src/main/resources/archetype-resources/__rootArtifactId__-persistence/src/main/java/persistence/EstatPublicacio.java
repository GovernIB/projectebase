#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.persistence;

/**
 * Representa l'estat d'una entitat. Si està activa o inactiva, o si està a l'històric.
 *
 * @author areus
 */
public enum EstatPublicacio {
    ACTIU,
    INACTIU,
    HISTORIC
}
