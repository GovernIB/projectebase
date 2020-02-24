#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb;

import ${package}.commons.i18n.I18NException;
import ${package}.commons.utils.Constants;
import ${package}.ejb.interceptor.Logged;
import ${package}.persistence.Procediment;
import ${package}.persistence.UnitatOrganica;
import ${package}.persistence.dao.ProcedimentDAO;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;


/**
 * Servei EJB per gestionar {@link Procediment}. Li aplicam l'interceptor {@link Logged}, per
 * tant, totes les cridades es loguejeran.
 *
 * @author areus
 * @author anadal
 */
@Logged
@Stateless
@RolesAllowed(Constants.${prefixuppercase}_ADMIN)
public class ProcedimentEJB extends ProcedimentDAO implements ProcedimentService {

    @Override
    public Procediment create(Procediment procediment, Long unitatOrganicaId) throws I18NException {
        try {
            UnitatOrganica unitatOrganica = entityManager.getReference(UnitatOrganica.class, unitatOrganicaId);
            procediment.setUnitatOrganica(unitatOrganica);
            entityManager.persist(procediment);
            return procediment;
        } catch (EntityNotFoundException e) {
            throw new I18NException("unitatorganica.noexisteix", String.valueOf(unitatOrganicaId));
        }
    }

}
