package es.caib.projectebase.ejb;

import es.caib.projectebase.commons.i18n.I18NException;
import es.caib.projectebase.jpa.UnitatOrganica;
import es.caib.projectebase.jpa.dao.IUnitatOrganicaDAO;

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
