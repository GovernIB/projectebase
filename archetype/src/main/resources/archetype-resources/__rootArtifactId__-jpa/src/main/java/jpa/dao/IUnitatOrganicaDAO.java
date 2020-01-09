#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jpa.dao;


import ${package}.jpa.UnitatOrganica;

/**
 * Interfície del servei per gestionar {@link UnitatOrganica}
 *
 * @author areus
 * @author anadal
 *
 */
public interface IUnitatOrganicaDAO extends DAO<UnitatOrganica, Long>  {

}
