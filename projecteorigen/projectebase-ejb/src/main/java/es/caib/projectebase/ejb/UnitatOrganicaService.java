package es.caib.projectebase.ejb;

import es.caib.projectebase.persistence.UnitatOrganica;
import es.caib.projectebase.persistence.dao.DAO;

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
