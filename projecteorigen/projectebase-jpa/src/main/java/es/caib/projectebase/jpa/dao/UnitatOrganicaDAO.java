package es.caib.projectebase.jpa.dao;

import es.caib.projectebase.jpa.UnitatOrganica;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * DAO per gestionar {@link UnitatOrganica}.
 *
 * @author areus
 */
public class UnitatOrganicaDAO extends GenericJpaDAO<Long, UnitatOrganica> {

    /**
     * Constructor per defecte.
     */
    public UnitatOrganicaDAO() {
        super(UnitatOrganica.class);
    }

    // MÈTODES ESPECÍFICS PER UNITATS ORGÀNIQUES

    /**
     * Obté una llista de les unitats orgàniques filtrades per una cadena de caràcters per paginacions.
     * La cadena de filtre es cerca dins els camps codiDir3 i nom.
     *
     * @param filter   cadena de caràcters que es cercarà dins codiDir3 i dins nom.
     * @param first    el número d'ordre de la primera unitat orgànica a tornar. La primera és 0.
     * @param pageSize el nombre màxim d'unitats orgàniques a tornar.
     * @return llista d'unitats orgàniques que compleixen el filtre.
     */
    public List<UnitatOrganica> findFiltered(String filter, @PositiveOrZero int first, @Positive int pageSize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UnitatOrganica> cq = cb.createQuery(UnitatOrganica.class);
        final Root<UnitatOrganica> root = cq.from(UnitatOrganica.class);
        cq.select(root);
        applyFilter(filter, cq, root);

        TypedQuery<UnitatOrganica> query = entityManager.createQuery(cq);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    /**
     * Retorna el nombre total d'unitats orgàniques que compleixen el filtre indicat.
     * La cadena de filtre es cerca dins els camps codiDir3 i nom.
     *
     * @param filter cadena de caràcters que es cercarà dins codiDir3 i dins nom.
     * @return nombre d'unitats orgàniques que compleixen el filtre.
     */
    public long countFiltered(String filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<UnitatOrganica> root = cq.from(UnitatOrganica.class);
        cq.select(cb.count(root));
        applyFilter(filter, cq, root);

        TypedQuery<Long> query = entityManager.createQuery(cq);
        return query.getSingleResult();
    }

    /**
     * Modifica l'objecte CriteriaQuery afegint el filtratge dels camps codiDir3 i nom.
     * @param filter Cadena a cercar dins el camps codiDir3 i nom.
     * @param cq query on aplicar el filtre.
     * @param root objecte emprant al from del select d'on s'agafen els camps codiDir3 i nom.
     */
    private void applyFilter(String filter, CriteriaQuery<?> cq, Root<UnitatOrganica> root) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        if (filter != null && !filter.isEmpty()) {
            String filterExpression = "%" + filter.toLowerCase() + "%";
            cq.where(
                    cb.or(
                            cb.like(cb.lower(root.get("codiDir3")), filterExpression),
                            cb.like(cb.lower(root.get("nom")), filterExpression)));
        }
    }

}
