package es.caib.projectebase.ejb.facade;

import es.caib.projectebase.commons.utils.Constants;
import es.caib.projectebase.ejb.converter.UnitatOrganicaConverter;
import es.caib.projectebase.ejb.interceptor.Logged;
import es.caib.projectebase.ejb.repository.QueryTemplateBuilder;
import es.caib.projectebase.ejb.repository.UnitatOrganicaRepository;
import es.caib.projectebase.persistence.model.UnitatOrganica;
import es.caib.projectebase.persistence.model.UnitatOrganica_;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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


        QueryTemplateBuilder<UnitatOrganica, UnitatOrganicaDTO> builder = new QueryTemplateBuilder<>(entityManager,
                UnitatOrganica.class, UnitatOrganicaDTO.class);

        List<UnitatOrganicaDTO> items = builder.selectConstruct(
                UnitatOrganica_.id,
                UnitatOrganica_.codiDir3,
                UnitatOrganica_.nom,
                UnitatOrganica_.dataCreacio,
                UnitatOrganica_.estat).withParams(filters).getPagedResult(firstResult, maxResult);

        long total = new QueryTemplateBuilder<UnitatOrganica, Long>(entityManager, UnitatOrganica.class, Long.class)
                .selectCount().withParams(filters).getSingleResult();

        /*
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UnitatOrganicaDTO> criteriaQuery = builder.createQuery(UnitatOrganicaDTO.class);
        Root<UnitatOrganica> root = criteriaQuery.from(UnitatOrganica.class);
        criteriaQuery.select(builder.construct(UnitatOrganicaDTO.class,
                root.get(UnitatOrganica_.id),
                root.get(UnitatOrganica_.codiDir3),
                root.get(UnitatOrganica_.nom),
                root.get(UnitatOrganica_.dataCreacio),
                root.get(UnitatOrganica_.estat)
        ));


        TypedQuery<UnitatOrganicaDTO> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        List<UnitatOrganicaDTO> items = query.getResultList();

        CriteriaQuery<Long> countCriteriaQuery = builder.createQuery(Long.class);
        Root<UnitatOrganica> rootCount = countCriteriaQuery.from(UnitatOrganica.class);
        countCriteriaQuery.select(builder.count(rootCount));

        TypedQuery<Long> countQuery = entityManager.createQuery(countCriteriaQuery);
        long total = countQuery.getSingleResult();
         */

        return new Page<>(items, total);
    }
}
