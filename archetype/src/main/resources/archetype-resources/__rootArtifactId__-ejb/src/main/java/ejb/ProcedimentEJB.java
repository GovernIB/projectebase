#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb;

import ${package}.commons.i18n.I18NException;
import ${package}.ejb.interceptor.Logged;
import ${package}.jpa.Procediment;
import ${package}.jpa.UnitatOrganica;
import ${package}.jpa.dao.ProcedimentDAO;

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