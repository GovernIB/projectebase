package es.caib.projectebase.ejb;

import es.caib.projectebase.commons.utils.Constants;
import es.caib.projectebase.ejb.interceptor.Logged;
import es.caib.projectebase.persistence.UnitatOrganica;
import es.caib.projectebase.persistence.dao.AbstractDAO;

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
@RolesAllowed(Constants.PBS_ADMIN)
public class UnitatOrganicaEJB extends AbstractDAO<UnitatOrganica, Long> implements UnitatOrganicaService {

}
