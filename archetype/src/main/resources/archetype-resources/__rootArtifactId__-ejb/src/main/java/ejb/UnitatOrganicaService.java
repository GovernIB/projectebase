#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb;

import ${package}.commons.i18n.I18NException;
import ${package}.jpa.UnitatOrganica;
import ${package}.jpa.dao.IUnitatOrganicaDAO;

import java.util.List;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;


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
