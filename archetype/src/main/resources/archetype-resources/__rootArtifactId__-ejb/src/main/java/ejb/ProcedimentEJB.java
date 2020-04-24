#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb;

import ${package}.commons.utils.Constants;
import ${package}.ejb.interceptor.Logged;
import ${package}.persistence.Procediment;
import ${package}.persistence.UnitatOrganica;
import ${package}.persistence.dao.AbstractDAO;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;


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
public class ProcedimentEJB extends AbstractDAO<Procediment, Long> implements ProcedimentService {

    // MÈTODES ESPECÍFICS PER PROCEDIMENTS

    @Override
    public Procediment create(Procediment procediment, Long unitatOrganicaId) throws ServiceException {
        try {
            UnitatOrganica unitatOrganica = entityManager.getReference(UnitatOrganica.class, unitatOrganicaId);
            procediment.setUnitatOrganica(unitatOrganica);
            entityManager.persist(procediment);
            return procediment;
        } catch (EntityNotFoundException e) {
            throw new ServiceException("unitatorganica.noexisteix", unitatOrganicaId);
        }
    }

    /**
     * Obté una llista de tots els procediments d'una unitat orgànica.
     *
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @return llista de procediments de la unitat orgànica.
     */
    @PermitAll
    public List<Procediment> findAllByUnitatOrganica(Long unitatOrganicaId) {
        return findAllByUnitatOrganica(unitatOrganicaId, null);
    }

    /**
     * Obté el nombre de procediments d'una unitat orgànica
     *
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @return nombre de procediments relacionats amb la unitat orgànica
     */
    @PermitAll
    public Long countAllByUnitatOrganica(Long unitatOrganicaId) {
        return countAllByUnitatOrganica(unitatOrganicaId, null);
    }

    /**
     * Obté una llista dels procediments d'una unitat orgànica que compleixen un filtre.
     * La cadena de filtre es cerca dins els camps de tipus string: codiSia i nom.
     * Si filter és <code>null</code> no aplica cap filtre.
     *
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @param filters          map on les claus són el nom d'atribut i el valor pel qual s'ha de filtrar.
     * @return llista de procediments de la unitat orgànica que compleixen el filtre.
     */
    @PermitAll
    public List<Procediment> findAllByUnitatOrganica(Long unitatOrganicaId, Map<String, Object> filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Procediment> cq = cb.createQuery(Procediment.class);
        final Root<Procediment> root = cq.from(Procediment.class);
        cq.select(root);
        cq.where(
                cb.and(
                        cb.equal(root.get("unitatOrganica").get("id"), unitatOrganicaId),
                        getFilterPredicate(root, filters)
                )
        );

        TypedQuery<Procediment> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    /**
     * Obté el nombre de procediments d'una unitat orgànica que compleixein un filtre.
     * Si filter és <code>null</code> no aplica cap filtre.
     *
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @param filters          map on les claus són el nom d'atribut i el valor pel qual s'ha de filtrar.
     * @return nombre de procediments relacionats amb la unitat orgànica que compleixen el filtre.
     */
    @PermitAll
    public long countAllByUnitatOrganica(Long unitatOrganicaId, Map<String, Object> filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<Procediment> root = cq.from(Procediment.class);
        cq.select(cb.count(root));
        cq.where(
                cb.and(
                        cb.equal(root.get("unitatOrganica").get("id"), unitatOrganicaId),
                        getFilterPredicate(root, filters)
                )
        );

        TypedQuery<Long> query = entityManager.createQuery(cq);
        return query.getSingleResult();
    }
}
