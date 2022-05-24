#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.distribucio.repository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;

import ${package}.distribucio.persistence.AnotacioInbox;

import java.util.List;

/**
 * Implementació del repositori de AnotacioInbox.
 *
 * @author areus
 */
@Stateless @Local(AnotacioInboxRepository.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AnotacioInboxRepositoryBean extends AbstractCrudRepository<AnotacioInbox, Long>
        implements AnotacioInboxRepository {

    protected AnotacioInboxRepositoryBean() {
        super(AnotacioInbox.class);
    }

    @Override
    public List<Long> findAllPendents() {
        TypedQuery<Long> query = entityManager.createNamedQuery(AnotacioInbox.FIND_ALL_BYESTAT, Long.class);
        query.setParameter("estat", AnotacioInbox.Estat.PENDENT);
        return query.getResultList();
    }

    @Override
    public List<Long> findAllRebudes() {
        TypedQuery<Long> query = entityManager.createNamedQuery(AnotacioInbox.FIND_ALL_BYESTAT, Long.class);
        query.setParameter("estat", AnotacioInbox.Estat.REBUDA);
        return query.getResultList();
    }
}
