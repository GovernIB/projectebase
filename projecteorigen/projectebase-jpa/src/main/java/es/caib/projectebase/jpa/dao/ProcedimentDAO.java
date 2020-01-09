package es.caib.projectebase.jpa.dao;

import es.caib.projectebase.jpa.Procediment;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * DAO per gestionar {@link Procediment}.
 *
 * @author areus
 */
public class ProcedimentDAO extends AbstractDAO<Procediment, Long> implements IProcedimentDAO {


    // MÈTODES ESPECÍFICS PER PROCEDIMENTS

    /**
     * Obté una llista de tots els procediments d'una unitat orgànica.
     *
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @return llista de procediments de la unitat orgànica.
     */
    public List<Procediment> findAllByUnitatOrganica(Long unitatOrganicaId) {
        return findAllByUnitatOrganica(unitatOrganicaId, null);
    }

    /**
     * Obté el nombre de procediments d'una unitat orgànica
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @return nombre de procediments relacionats amb la unitat orgànica
     */
    public Long countAllByUnitatOrganica(Long unitatOrganicaId) {
        return countAllByUnitatOrganica(unitatOrganicaId, null);
    }

    /**
     * Obté una llista dels procediments d'una unitat orgànica que compleixen un filtre.
     * La cadena de filtre es cerca dins els camps de tipus string: codiSia i nom.
     * Si filter és <code>null</code> no aplica cap filtre.
     *
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @param filter cadena de caràcters que es cercarà dins els camps de tipus string: codiSia i nom.
     * @return llista de procediments de la unitat orgànica que compleixen el filtre.
     */
    public List<Procediment> findAllByUnitatOrganica(Long unitatOrganicaId, String filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Procediment> cq = cb.createQuery(Procediment.class);
        final Root<Procediment> root = cq.from(Procediment.class);
        cq.select(root);
        cq.where(
                cb.and(
                        cb.equal(root.get("unitatOrganica").get("id"), unitatOrganicaId),
                        getFilterPredicate(root, filter)
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
     * @param filter cadena de caràcters que es cercarà dins els camps de tipus string: codiSia i nom.
     * @return nombre de procediments relacionats amb la unitat orgànica que compleixen el filtre.
     */
    public Long countAllByUnitatOrganica(Long unitatOrganicaId, String filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<Procediment> root = cq.from(Procediment.class);
        cq.select(cb.count(root));
        cq.where(
                cb.and(
                        cb.equal(root.get("unitatOrganica").get("id"), unitatOrganicaId),
                        getFilterPredicate(root, filter)
                )
        );

        TypedQuery<Long> query = entityManager.createQuery(cq);
        return query.getSingleResult();
    }
    
    
    @Override
    public String getJPATableName() {
        return "Procediment";
    }

    @Override
    public Class<Procediment> getJPAClass() {
        return Procediment.class;
    }

    @Override
    public Long getJPAPrimaryKey(Procediment entity) {
        if (entity == null) {
            return null;
        } else {
            return entity.getId();
        }
    } 
    
}
