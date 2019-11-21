package es.caib.projectebase.service;

import es.caib.projectebase.interceptor.Logged;
import es.caib.projectebase.jpa.UnitatOrganica;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Servei EJB per gestionar {@link UnitatOrganica}
 * @author areus
 */
@Logged
@Stateless
//@RolesAllowed({"tothom", "PB_ADMIN"})
public class UnitatOrganicaEJB implements UnitatOrganicaService {

	@PersistenceContext
	EntityManager entityManager;

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
}
