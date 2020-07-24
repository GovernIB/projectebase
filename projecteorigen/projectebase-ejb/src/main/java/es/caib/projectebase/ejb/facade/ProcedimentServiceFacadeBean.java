package es.caib.projectebase.ejb.facade;

import es.caib.projectebase.commons.utils.Constants;
import es.caib.projectebase.ejb.converter.ProcedimentConverter;
import es.caib.projectebase.ejb.interceptor.Logged;
import es.caib.projectebase.ejb.repository.ProcedimentRepository;
import es.caib.projectebase.ejb.repository.UnitatOrganicaRepository;
import es.caib.projectebase.persistence.model.Procediment;
import es.caib.projectebase.persistence.model.UnitatOrganica;
import es.caib.projectebase.service.facade.ProcedimentServiceFacade;
import es.caib.projectebase.service.model.Page;
import es.caib.projectebase.service.model.ProcedimentDTO;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Logged
@Stateless
@Local(ProcedimentServiceFacade.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@RolesAllowed(Constants.PBS_ADMIN)
public class ProcedimentServiceFacadeBean implements ProcedimentServiceFacade {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private UnitatOrganicaRepository unitatRepository;

    @Inject
    private ProcedimentRepository repository;

    @Inject
    private ProcedimentConverter converter;

    @Override
    public Long create(ProcedimentDTO dto, Long idUnitat) {
        UnitatOrganica unitatReference = unitatRepository.getReference(idUnitat);

        Procediment procediment = converter.toEntity(dto);
        procediment.setUnitatOrganica(unitatReference);
        repository.create(procediment);
        return procediment.getId();
    }

    @Override
    public void update(ProcedimentDTO dto) {
        Procediment procediment = repository.getReference(dto.getId());
        converter.updateFromDTO(procediment, dto);
    }

    @Override
    public void delete(Long id) {
        Procediment procediment = entityManager.getReference(Procediment.class, id);
        repository.delete(procediment);
    }

    @Override
    public ProcedimentDTO findById(Long id) {
        Procediment procediment = repository.findById(id);
        return converter.toDTO(procediment);
    }

    @Override
    public Page<ProcedimentDTO> findByUnitat(int firstResult, int maxResult, Long idUnitat) {
        TypedQuery<ProcedimentDTO> query = entityManager.createQuery(
                "select new es.caib.projectebase.service.model.ProcedimentDTO(p.id, p.codiSia, p.nom, u.id) " +
                        "from Procediment p join p.unitatOrganica u where u.id = :idUnitat", ProcedimentDTO.class);
        query.setParameter("idUnitat", idUnitat);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        List<ProcedimentDTO> items = query.getResultList();

        TypedQuery<Long> countQuery = entityManager.createQuery(
                "select count(p) from Procediment p join p.unitatOrganica u " +
                        "where u.id = :idUnitat", Long.class);
        countQuery.setParameter("idUnitat", idUnitat);
        long total = countQuery.getSingleResult();

        return new Page<>(items, total);
    }
}
