#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb;

import ${package}.commons.i18n.I18NException;
import ${package}.persistence.Procediment;
import ${package}.persistence.dao.IProcedimentDAO;

import javax.ejb.Local;


/**
 * Interfície del servei per gestionar {@link Procediment}
 *
 * @author areus
 * @author anadal
 */
@Local
public interface ProcedimentService extends IProcedimentDAO {

    
    /**
     * Crea un procediment depenent d'una unitat orgànica.
     *
     * @param procediment
     *            procediment.
     * @param unitatOrganicaId
     *            identificador de la unitat orgànica.
     * @return El procediment creat.
     */
    Procediment create(Procediment procediment, Long unitatOrganicaId) throws I18NException;
    
    
}
