package es.caib.projectebase.ejb;

import es.caib.projectebase.commons.i18n.I18NException;
import es.caib.projectebase.persistence.UnitatOrganica;
import es.caib.projectebase.persistence.dao.IUnitatOrganicaDAO;

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
