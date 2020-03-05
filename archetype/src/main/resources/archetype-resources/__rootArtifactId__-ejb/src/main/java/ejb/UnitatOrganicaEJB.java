#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb;

import ${package}.commons.utils.Constants;
import ${package}.ejb.interceptor.Logged;
import ${package}.persistence.UnitatOrganica;
import ${package}.persistence.dao.AbstractDAO;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

/**
 * Servei EJB per gestionar {@link UnitatOrganica}. Li aplicam l'interceptor {@link Logged},
 * per tant, totes les cridades es loguejeran.
 *
 * @author areus
 */
@Logged
@Stateless
@RolesAllowed(Constants.${prefixuppercase}_ADMIN)
public class UnitatOrganicaEJB extends AbstractDAO<UnitatOrganica, Long> implements UnitatOrganicaService {

}
