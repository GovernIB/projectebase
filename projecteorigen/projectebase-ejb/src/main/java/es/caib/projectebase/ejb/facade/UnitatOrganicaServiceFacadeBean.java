package es.caib.projectebase.ejb.facade;

import es.caib.projectebase.commons.utils.Constants;
import es.caib.projectebase.ejb.converter.UnitatOrganicaConverter;
import es.caib.projectebase.ejb.interceptor.Logged;
import es.caib.projectebase.ejb.repository.UnitatOrganicaRepository;
import es.caib.projectebase.persistence.model.UnitatOrganica;
import es.caib.projectebase.service.facade.UnitatOrganicaServiceFacade;
import es.caib.projectebase.service.model.Page;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;

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
import java.util.Map;

@Logged
@Stateless
@Local(UnitatOrganicaServiceFacade.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@RolesAllowed(Constants.PBS_ADMIN)
public class UnitatOrganicaServiceFacadeBean implements UnitatOrganicaServiceFacade {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private UnitatOrganicaRepository repository;

    @Inject
    private UnitatOrganicaConverter converter;

    @Override
    public Long create(UnitatOrganicaDTO dto) {
        UnitatOrganica unitat = converter.toEntity(dto);
        repository.create(unitat);
        return unitat.getId();
    }

    @Override
    public void update(UnitatOrganicaDTO dto) {
        UnitatOrganica unitat = repository.getReference(dto.getId());
        converter.updateFromDTO(unitat, dto);
    }

    @Override
    public void delete(Long id) {
        UnitatOrganica unitat = repository.getReference(id);
        repository.delete(unitat);
    }

    @Override
    public UnitatOrganicaDTO findById(Long id) {
        UnitatOrganica unitat = repository.findById(id);
        return converter.toDTO(unitat);
    }

    @Override
    public Page<UnitatOrganicaDTO> findFiltered(int firstResult, int maxResult, Map<String, Object> filters) {
        TypedQuery<UnitatOrganicaDTO> query = entityManager.createQuery(
                "select new es.caib.projectebase.service.model.UnitatOrganicaDTO(u.id, u.codiDir3, u.nom, " +
                        "u.dataCreacio, u.estat) " +
                        "from UnitatOrganica u", UnitatOrganicaDTO.class);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        List<UnitatOrganicaDTO> items = query.getResultList();

        TypedQuery<Long> countQuery = entityManager.createQuery(
                "select count(u) from UnitatOrganica u", Long.class);
        long total = countQuery.getSingleResult();

        return new Page<>(items, total);
    }
}
