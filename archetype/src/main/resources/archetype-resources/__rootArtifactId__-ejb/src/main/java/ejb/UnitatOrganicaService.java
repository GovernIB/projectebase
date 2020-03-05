#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb;

import ${package}.persistence.UnitatOrganica;
import ${package}.persistence.dao.DAO;

import javax.ejb.Local;

/**
 * Interfície del servei per gestionar {@link UnitatOrganica}
 *
 * @author areus
 * @author anadal
 */
@Local
public interface UnitatOrganicaService extends DAO<UnitatOrganica, Long> {

}
