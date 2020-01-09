#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.commons.i18n.I18NArgumentString;
import ${package}.commons.i18n.I18NException;
import ${package}.commons.query.OrderBy;
import ${package}.commons.query.OrderType;

/**
 * /**
 * Implementació bàsica d'un {@link DAO}.
 *
 * @param <K> Tipus de la clau primària de l'entitat.
 * @param <E> Tipus de l'entitat.
 *
 * @author areus
 * @author anadal
 */
public abstract class AbstractDAO<E extends Serializable, PK> implements DAO<E, PK> {

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
    public void bulkCreate( List<E> entities) throws I18NException {
        if (entities != null) {
            for (E entity : entities) {
                create(entity);
            }
        }
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
    public Long countAll() {
        TypedQuery<Long> query = entityManager.createQuery("select count(p) from " + getJPATableName(), Long.class);
        return query.getSingleResult();
    }
    
    
    /**
     * Retorna el nombre total d'entitats que compleixen el filtre indicat.
     * La cadena de filtre es cerca dins tots els camps de tipus String de l'entitat.
     *
     * @param filter   cadena de caràcters que es cercarà dins els camps de tipus string.
     * @return nombre d'entitats que compleixen el filtre.
     */
    @Override
    public long countFilter(String filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<E> root = cq.from(getJPAClass());
        cq.select(cb.count(root));
        cq.where(getFilterPredicate(root, filter));

        TypedQuery<Long> query = entityManager.createQuery(cq);
        return query.getSingleResult();
    }

    /**
     * Obte totes les entrades de la bbdd
     * 
     * @return
     * @throws Exception
     */
    @Override
    public List<E> selectAll(OrderBy... orderBy) throws I18NException {
        return select(null, null, null, orderBy);
    }
    
    
    public List<E> selectAll(String filter, OrderBy... orderBy) throws I18NException {
        return select(filter, null, null, orderBy);
    }

    /**
     * 
     * @param first
     * @param pageSize
     * @return
     */
    @Override
    public List<E> selectAll(@PositiveOrZero int first, @Positive int pageSize, OrderBy... orderBy)
            throws I18NException {
        return select(null, first, pageSize, orderBy);
    }

    /**
     * 
     * @param first
     * @param pageSize
     * @return
     */
    @Override
    public List<E> selectAll(String filter, @PositiveOrZero int first, @Positive int pageSize, OrderBy... orderBy)
            throws I18NException {
        return select(filter, first, pageSize, orderBy);
    }

    /**
     * Obté una llista de les entitats filtrades per una cadena de caràcters per paginacions.
     * La cadena de filtre es cerca dins tots els camps de tipus String de l'entitat.
     *
     * @param filter   cadena de caràcters que es cercarà dins els camps de tipus string.
     * @param first    el número d'ordre de la primera entitat a tornar. La primera és 0.
     * @param pageSize el nombre màxim d'entitats a tornar.
     * @param orderBy Camps per ordenar el llistat
     * @return llista d'entitats que compleixen el filtre.
     * @throws I18NException
     */
    protected List<E> select(String filter, Integer firstResult, Integer maxResults, OrderBy... orderByList) throws I18NException {

        
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(getJPAClass());
        final Root<E> root = cq.from(getJPAClass());
        cq.select(root);
        
        if (filter != null && filter.trim().length() != 0) {
          cq.where(getFilterPredicate(root, filter));
        }

        
        // select XYZ ZZZ
        //StringBuffer query = new StringBuffer("select * ");
        // from
        //query.append(" from " + getJPATableName() + " tt ");

        // order by
        if (orderByList != null && orderByList.length != 0) {
            //query.append(" order by ");
            //query.append(OrderBy.processOrderBy(orderBy));
            
            
            List<Order> orderList = new ArrayList<Order>();
            for (OrderBy orderBy : orderByList) {
                if (orderBy.orderType == OrderType.ASC) {
                    orderList.add(cb.desc(root.get(orderBy.javaName)));
                } else {
                    orderList.add(cb.asc(root.get(orderBy.javaName)));
                }
            }
            cq.orderBy(orderList);
        }

        // XYZ ZZZ String __query = query.toString();

        Query q = entityManager.createQuery(cq);

        if (firstResult != null) {
            q.setFirstResult(firstResult);
        }
        if (maxResults != null) {
            q.setMaxResults(maxResults);
        }
        try {
            return q.getResultList();
        } catch (Exception e) {
            log.error("Error executant la sentència SQL: " + q.toString());
            throw new I18NException(e, "error.query", new I18NArgumentString(q.toString()),
                    new I18NArgumentString(e.getMessage()));
        }
    }
    
    
    
    /**
     * Obté un predicat per emprar amb un where per aplicar un filtre de cercar un text sobre tots els camps string.
     *
     * @param root objecte emprant al from del select d'on s'agafen els camps.
     * @param filter Cadena a cercar dins els camps de tipus string.
     * @return predicat per emprar a un where per aplicar el filtre. Si filter és <code>null</code> o buid, retorna
     * un predicat que sempre avalua a <code>true</code>.
     */
    protected Predicate getFilterPredicate(Root<E> root, String filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        if (filter == null || filter.isEmpty()) {
            // No imposa cap restricció, el predicat sempre avalua a true.
            return cb.conjunction();
        }

        String filterExpression = "%" + filter.toLowerCase() + "%";
        // Obté la llista de camps simples de l'entitat. Inclou els declarats a superclasses
        // Si es volen només els declarats explicitament a l'entitat es pot emprar getDeclaredSingularAttributes()
        Set<SingularAttribute<? super E, ?>> singularAttributes = root.getModel().getSingularAttributes();

        // Per cada atribut de tipus String afegirem un LIKE al WHERE enllaçats amb OR
        /* Manera tradicional */
        /*
        List<Predicate> predicateList = new ArrayList<>();
        for (SingularAttribute<? super E, ?> attribute : singularAttributes) {
            if (attribute.getJavaType().equals(String.class)) {
                Path<String> propietat = (Path<String>) root.get(attribute);
                predicateList.add(cb.like(cb.lower(propietat), filterExpression));
            }
        }
        Predicate[] predicates = predicateList.toArray(new Predicate[0]);
        return cb.or(predicates);
        */

        /* Manera nova, amb expresions lambda. */
        return cb.or(singularAttributes.stream() // Itera tots els atributs
                .filter(attribute -> attribute.getJavaType().equals(String.class)) // filtra els que són de tipus String
                .map(attribute -> (Path<String>) root.get(attribute)) // Agafa el camp
                .map(propietat -> cb.like(cb.lower(propietat), filterExpression)) // El converteix en un predicat LIKE
                .toArray(Predicate[]::new)); // I ho recull tot a un array de predicats
    }
    
    
    
    
    @Override
    public E getReference(PK id) {
        return entityManager.getReference(getJPAClass(), id);
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
