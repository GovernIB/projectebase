#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.interceptor.Logged;
import ${package}.jpa.UnitatOrganica;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * Servei EJB per gestionar {@link UnitatOrganica}.
 * Li aplicam l'interceptor {@link Logged}, per tant, totes les cridades es loguejeran.
 *
 * @author areus
 */
@Logged
@Stateless
public class UnitatOrganicaEJB implements UnitatOrganicaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void bulkCreate(@NotNull List<UnitatOrganica> unitats) {
        unitats.forEach(entityManager::persist);
    }

    @Override
    public UnitatOrganica create(UnitatOrganica unitatOrganica) {
        entityManager.persist(unitatOrganica);
        return unitatOrganica;
    }

    @Override
    public UnitatOrganica update(UnitatOrganica unitatOrganica) {
        return entityManager.merge(unitatOrganica);
    }

    @Override
    public void deleteById(Long id) {
        UnitatOrganica unitatOrganica = entityManager.getReference(UnitatOrganica.class, id);
        entityManager.remove(unitatOrganica);
    }

    @Override
    public UnitatOrganica findById(Long id) {
        return entityManager.find(UnitatOrganica.class, id);
    }

    @Override
    public List<UnitatOrganica> findAll() {
        TypedQuery<UnitatOrganica> query = entityManager.createQuery(
                "select uo from UnitatOrganica uo", UnitatOrganica.class);
        return query.getResultList();
    }

    @Override
    public List<UnitatOrganica> findAllPaged(@PositiveOrZero int first, @Positive int pageSize) {
        TypedQuery<UnitatOrganica> query = entityManager.createQuery(
                "select uo from UnitatOrganica uo", UnitatOrganica.class);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public long countAll() {
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(uo) from UnitatOrganica uo", Long.class);
        return query.getSingleResult();
    }

    @Override
    public List<UnitatOrganica> findFilteredPaged(String filter, @PositiveOrZero int first, @Positive int pageSize) {
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

    @Override
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
    private void applyFilter(String filter, CriteriaQuery cq, Root<UnitatOrganica> root) {
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
