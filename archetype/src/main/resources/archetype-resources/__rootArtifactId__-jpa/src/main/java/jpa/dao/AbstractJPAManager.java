#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.commons.i18n.I18NArgumentString;
import ${package}.commons.i18n.I18NException;
import ${package}.commons.query.OrderBy;

/**
 * 
 * @author anadal
 *
 * @param <E>
 * @param <PK>
 */
public abstract class AbstractJPAManager<E extends Serializable, PK> implements IAbstractJPAManager<E, PK> {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public E create(E entity) throws I18NException {
        try {
            entityManager.persist(entity);
        } catch (javax.persistence.EntityExistsException eee) {
            throw new I18NException("entity.error.alreadyexists", entity.getClass().getName());
        } catch (Throwable e) {
            // entity.error.creant=Error desconocido creando un objeto de tipo {0}: {1}
            throw new I18NException("entity.error.creating", entity.getClass().getName(), e.getMessage());
        }
        return entity;
    }

    @Override
    public E update(E entity) throws I18NException {
        try {
            return entityManager.merge(entity);
        } catch (Throwable e) {
            // entity.error.updating=Error desconocido actualizando un objeto de tipo {0}: {1}
            throw new I18NException("entity.error.updating", entity.getClass().getName(), e.getMessage());
        }
    }

    @Override
    public void delete(E entity) { // throws I18NException
        if (entity != null) {
            // try {
            entityManager.remove(entity);
            /*
             * } catch (Throwable e) { //entity.error.deleting=Error desconegut esborrant un
             * objecte de tipus {0} amb ID {1}: {1} throw new
             * I18NException("entity.error.deleting", entity.getClass().getName(),
             * String.valueOf(getPrimaryKey(entity)), e.getMessage()); }
             */
        }
    }

    @Override
    public void deleteById(PK id) { // throws I18NException
        if (id != null) {
            delete(entityManager.getReference(getJPAClass(), id));
        }
    }

    @Override
    public E findById(PK id) {
        return id == null ? null : (E) entityManager.find(getJPAClass(), id);
    }

    @Override
    public Long count() {
        TypedQuery<Long> query = entityManager.createQuery("select count(p) from " + getJPATableName(), Long.class);
        return query.getSingleResult();
    }

    /**
     * Obte totes les entrades de la bbdd
     * 
     * @return
     * @throws Exception
     */
    public List<E> selectAll(OrderBy... orderBy) throws I18NException {
        return select(null, null, orderBy);
    }

    /**
     * 
     * @param first
     * @param pageSize
     * @return
     */
    public List<E> selectPagination(@PositiveOrZero int first, @Positive int pageSize, OrderBy... orderBy)
            throws I18NException {
        return select(first, pageSize, orderBy);
    }

    /**
     * 
     * @param firstResult
     * @param maxResults
     * @param orderBy
     * @return
     * @throws I18NException
     */
    protected List<E> select(Integer firstResult, Integer maxResults, OrderBy... orderBy) throws I18NException {

        // select
        StringBuffer query = new StringBuffer("select * ");
        // from
        query.append(" from " + getJPATableName() + " tt ");

        // order by
        if (orderBy != null && orderBy.length != 0) {
            query.append(" order by ");
            query.append(OrderBy.processOrderBy(orderBy));
        }

        String __query = query.toString();

        Query q = entityManager.createQuery(__query);

        if (firstResult != null) {
            q.setFirstResult(firstResult);
        }
        if (maxResults != null) {
            q.setMaxResults(maxResults);
        }
        try {
            return q.getResultList();
        } catch (Exception e) {
            log.error("Error executant la sentència SQL: " + __query.toString());
            throw new I18NException(e, "error.query", new I18NArgumentString(__query.toString()),
                    new I18NArgumentString(e.getMessage()));
        }
    }

    /**
     * 
     * @return
     */
    public abstract String getJPATableName();

    /**
     * 
     * @return
     */
    public abstract Class<E> getJPAClass();

    /**
     * 
     * @param entity
     * @return
     */
    public abstract PK getJPAPrimaryKey(E entity);

}
