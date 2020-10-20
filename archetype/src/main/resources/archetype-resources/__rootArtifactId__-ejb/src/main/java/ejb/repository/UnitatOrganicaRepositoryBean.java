#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb.repository;

import ${package}.persistence.model.UnitatOrganica;
import ${package}.persistence.model.UnitatOrganica_;
import ${package}.service.model.Ordre;
import ${package}.service.model.UnitatOrganicaDTO;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementació del repositori d'Unitats Orgàniques.
 *
 * @author areus
 */
@Stateless @Local(UnitatOrganicaRepository.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UnitatOrganicaRepositoryBean extends AbstractCrudRepository<UnitatOrganica, Long>
        implements UnitatOrganicaRepository {

    protected UnitatOrganicaRepositoryBean() {
        super(UnitatOrganica.class);
    }

    @Override
    public Optional<UnitatOrganica> findByCodiDir3(String codiDir3) {
        TypedQuery<UnitatOrganica> query = entityManager.createNamedQuery(
                UnitatOrganica.FIND_BY_CODIDIR3,
                UnitatOrganica.class);
        query.setParameter("codiDir3", codiDir3);
        List<UnitatOrganica> result = query.getResultList();
        return Optional.ofNullable(result.isEmpty() ? null : result.get(0));
    }

    @Override
    public List<UnitatOrganicaDTO> findPagedByFilterAndOrder(int firstResult, int maxResult,
                                                             Map<String, Object> filters,
                                                             List<Ordre> ordenacio) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UnitatOrganicaDTO> criteriaQuery = builder.createQuery(UnitatOrganicaDTO.class);
        Root<UnitatOrganica> root = criteriaQuery.from(UnitatOrganica.class);

        criteriaQuery.select(builder.construct(UnitatOrganicaDTO.class,
                root.get(UnitatOrganica_.id),
                root.get(UnitatOrganica_.codiDir3),
                root.get(UnitatOrganica_.nom),
                root.get(UnitatOrganica_.dataCreacio),
                root.get(UnitatOrganica_.estat)));

        CriteriaHelper helper = CriteriaHelper.getInstance(builder, root);
        helper.applyFilters(filters, criteriaQuery);
        helper.applyOrder(ordenacio, criteriaQuery);

        TypedQuery<UnitatOrganicaDTO> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        return query.getResultList();
    }

    @Override
    public long countByFilter(Map<String, Object> filters) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<UnitatOrganica> root = criteriaQuery.from(UnitatOrganica.class);

        criteriaQuery.select(builder.count(root));

        CriteriaHelper helper = CriteriaHelper.getInstance(builder, root);
        helper.applyFilters(filters, criteriaQuery);

        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
