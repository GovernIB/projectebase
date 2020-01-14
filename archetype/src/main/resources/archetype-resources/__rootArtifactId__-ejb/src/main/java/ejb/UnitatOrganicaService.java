#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb;

import ${package}.commons.i18n.I18NException;
import ${package}.persistence.UnitatOrganica;
import ${package}.persistence.dao.IUnitatOrganicaDAO;

import javax.ejb.Local;

/**
 * Interfície del servei per gestionar {@link UnitatOrganica}
 *
 * @author areus
 * @author anadal
 */
@Local
public interface UnitatOrganicaService extends IUnitatOrganicaDAO {

    void testTranslationError() throws I18NException;

}
