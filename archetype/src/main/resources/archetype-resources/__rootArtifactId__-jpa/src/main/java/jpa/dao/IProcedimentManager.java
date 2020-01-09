#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jpa.dao;

import ${package}.jpa.Procediment;


/**
 * Interfície del servei per gestionar {@link Procediment}
 *
 * @author areus
 * @author anadal
 */
public interface IProcedimentManager extends IAbstractJPAManager<Procediment, Long> {

}
