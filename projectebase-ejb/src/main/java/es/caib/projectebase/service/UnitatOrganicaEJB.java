package es.caib.projectebase.service;

import es.caib.projectebase.interceptor.Logged;
import es.caib.projectebase.jpa.UnitatOrganica;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
}
