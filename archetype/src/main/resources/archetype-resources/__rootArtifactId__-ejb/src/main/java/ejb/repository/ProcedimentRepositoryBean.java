#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb.repository;

import ${package}.persistence.model.Procediment;
import ${package}.service.model.ProcedimentDTO;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Implementació del repositori de Procediments.
 *
 * @author areus
 */
@Stateless
@Local(ProcedimentRepository.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ProcedimentRepositoryBean extends AbstractCrudRepository<Procediment, Long>
        implements ProcedimentRepository {

    protected ProcedimentRepositoryBean() {
        super(Procediment.class);
    }

    @Override
    public Optional<Procediment> findByCodiSia(String codiSia) {
        TypedQuery<Procediment> query = entityManager.createQuery(
                "select p from Procediment p where p.codiSia = :codiSia", Procediment.class);
        query.setParameter("codiSia", codiSia);
        List<Procediment> result = query.getResultList();
        return Optional.ofNullable(result.isEmpty() ? null : result.get(0));
    }

    @Override
    public List<ProcedimentDTO> findPagedByUnitat(int firstResult, int maxResult, Long idUnitat) {

        TypedQuery<ProcedimentDTO> query = entityManager.createQuery(
                "select new ${package}.service.model.ProcedimentDTO(p.id, p.codiSia, p.nom, u.id) " +
                        "from Procediment p join p.unitatOrganica u where u.id = :idUnitat", ProcedimentDTO.class);
        query.setParameter("idUnitat", idUnitat);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        return query.getResultList();
    }

    @Override
    public long countByUnitat(Long idUnitat) {
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(p) from Procediment p join p.unitatOrganica u " +
                        "where u.id = :idUnitat", Long.class);
        query.setParameter("idUnitat", idUnitat);
        return query.getSingleResult();
    }
}
