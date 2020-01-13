package es.caib.projectebase.ejb;

import es.caib.projectebase.commons.i18n.I18NException;
import es.caib.projectebase.commons.utils.Constants;
import es.caib.projectebase.ejb.interceptor.Logged;
import es.caib.projectebase.jpa.Procediment;
import es.caib.projectebase.jpa.UnitatOrganica;
import es.caib.projectebase.jpa.dao.ProcedimentDAO;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;


/**
 * Servei EJB per gestionar {@link Procediment}. Li aplicam l'interceptor {@link Logged}, per
 * tant, totes les cridades es loguejeran.
 *
 * @author areus
 * @author anadal
 */
@Logged
@Stateless
@RolesAllowed(Constants.PBS_ADMIN)
public class ProcedimentEJB extends ProcedimentDAO implements ProcedimentService {

    @Override
    public Procediment create(Procediment procediment, Long unitatOrganicaId) throws I18NException {
        UnitatOrganica unitatOrganica;
        try {
            unitatOrganica = entityManager.getReference(UnitatOrganica.class, unitatOrganicaId);
        } catch (java.lang.IllegalArgumentException e) {
            throw new I18NException("unitatorganica.noexisteix", String.valueOf(unitatOrganicaId));
        }

        procediment.setUnitatOrganica(unitatOrganica);
        entityManager.persist(procediment);
        return procediment;
    }

}
