#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb;

import ${package}.commons.i18n.I18NException;
import ${package}.ejb.interceptor.Logged;
import ${package}.jpa.Procediment;
import ${package}.jpa.UnitatOrganica;
import ${package}.jpa.dao.ProcedimentManager;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import java.util.List;

/**
 * Servei EJB per gestionar {@link Procediment}. Li aplicam l'interceptor {@link Logged}, per
 * tant, totes les cridades es loguejeran.
 *
 * @author areus
 * @author anadal
 */
@Logged
@Stateless
public class ProcedimentEJB extends ProcedimentManager implements ProcedimentService {

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

    /*
     * @Override public Procediment update(Procediment procediment) { return
     * entityManager.merge(procediment); }
     * 
     * @Override public void deleteById(Long id) { Procediment procediment =
     * entityManager.getReference(Procediment.class, id); entityManager.remove(procediment); }
     * 
     * @Override public Procediment findById(Long id) { return
     * entityManager.find(Procediment.class, id); }
     */

    @Override
    public List<Procediment> findAllByUnitatOrganica(Long unitatOrganicaId) {
        TypedQuery<Procediment> query = entityManager.createQuery(
                "select p from " + getJPATableName() + " p where p.unitatOrganica.id = :unitatOrganicaId",
                Procediment.class);
        query.setParameter("unitatOrganicaId", unitatOrganicaId);
        return query.getResultList();
    }

    @Override
    public Long countAllByUnitatOrganica(Long unitatOrganicaId) {
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(p) from " + getJPATableName() + " p where p.unitatOrganica.id = :unitatOrganicaId",
                Long.class);
        query.setParameter("unitatOrganicaId", unitatOrganicaId);
        return query.getSingleResult();
    }

}
