#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.persistence.dao;


import ${package}.persistence.UnitatOrganica;

/**
 * Interfície del servei per gestionar {@link UnitatOrganica}
 *
 * @author areus
 * @author anadal
 *
 */
public interface IUnitatOrganicaDAO extends DAO<UnitatOrganica, Long>  {

}
