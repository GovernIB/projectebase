package es.caib.projectebase.service;

import es.caib.projectebase.interceptor.Logged;
import es.caib.projectebase.jpa.Procediment;
import es.caib.projectebase.jpa.UnitatOrganica;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Servei EJB per gestionar {@link Procediment}.
 * Li aplicam l'interceptor {@link Logged}, per tant, totes les cridades es loguejeran.
 *
 * @author areus
 */
@Logged
@Stateless
public class ProcedimentEJB implements ProcedimentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Procediment create(Procediment procediment, Long unitatOrganicaId) {
        UnitatOrganica unitatOrganica = entityManager.getReference(UnitatOrganica.class, unitatOrganicaId);
        procediment.setUnitatOrganica(unitatOrganica);
        entityManager.persist(procediment);
        return procediment;
    }

    @Override
    public Procediment update(Procediment procediment) {
        return entityManager.merge(procediment);
    }

    @Override
    public void deleteById(Long id) {
        Procediment procediment = entityManager.getReference(Procediment.class, id);
        entityManager.remove(procediment);
    }

    @Override
    public Procediment findById(Long id) {
        return entityManager.find(Procediment.class, id);
    }

    @Override
    public List<Procediment> findAllByUnitatOrganica(Long unitatOrganicaId) {
        TypedQuery<Procediment> query = entityManager.createQuery(
                "select p from Procediment p where p.unitatOrganica.id = :unitatOrganicaId", Procediment.class);
        query.setParameter("unitatOrganicaId", unitatOrganicaId);
        return query.getResultList();
    }
}
