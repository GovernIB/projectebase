package es.caib.projectebase.persistence.dao;

import es.caib.projectebase.commons.query.OrderBy;
import es.caib.projectebase.commons.query.OrderType;
import es.caib.projectebase.commons.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementació bàsica d'un {@link DAO}.
 *
 * @param <E>  Tipus de l'entitat.
 * @param <PK> Tipus de la clau primària de l'entitat.
 *
 * @author areus
 * @author anadal
 */
@RolesAllowed(Constants.PBS_ADMIN)
public abstract class AbstractDAO<E extends Serializable, PK> implements DAO<E, PK> {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * Guarda la classe del tipus d'entitat.
     */
    private final Class<E> entityClass;

    /**
     * Construtor per defecte. Emmagatzema a {@link #entityClass} el tipus d'entitat.
     */
    @SuppressWarnings("unchecked")
    protected AbstractDAO() {
        Class<?> clazz = getClass();
        while (!clazz.getSuperclass().equals(AbstractDAO.class)) {
            clazz = clazz.getSuperclass();
        }
        ParameterizedType genericSuperclass = (ParameterizedType) clazz.getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }

    // IMPLEMENTACIÓ DELS MÈTODES DAO

    @Override
    public E create(@NotNull E entity) throws DAOException {
        try {
            entityManager.persist(entity);
        } catch (EntityExistsException eee) {
            throw new DAOException("persistence.error.alreadyexists", entityClass.getName());
        } catch (PersistenceException pe) {
            throw new DAOException(pe, "persistence.error.unexpected");
        }
        return entity;
    }

    @Override
    public E update(@NotNull E entity) throws DAOException {
        try {
            return entityManager.merge(entity);
        } catch (PersistenceException pe) {
            throw new DAOException(pe, "persistence.error.unexpected");
        }
    }

    @Override
    public void delete(@NotNull PK id) throws DAOException {
        try {
            E entity = getReference(id);
            entityManager.remove(entity);
        } catch (EntityNotFoundException e) {
            throw new DAOException("persistence.error.notexists", entityClass.getName(), id);
        } catch (PersistenceException pe) {
            throw new DAOException(pe, "persistence.error.unexpected");
        }
    }

    @Override
    @PermitAll
    public E findById(@NotNull PK id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    @PermitAll
    public long countAll() throws DAOException {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<E> root = cq.from(entityClass);
        cq.select(cb.count(root));
        TypedQuery<Long> query = entityManager.createQuery(cq);
        try {
            return query.getSingleResult();
        } catch (PersistenceException pe) {
            throw new DAOException(pe, "persistence.error.unexpected");
        }
    }

    @Override
    @PermitAll
    public long countFiltered(Map<String, Object> filters) throws DAOException {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<E> root = cq.from(entityClass);
        cq.select(cb.count(root));
        cq.where(getFilterPredicate(root, filters));

        TypedQuery<Long> query = entityManager.createQuery(cq);
        try {
            return query.getSingleResult();
        } catch (PersistenceException pe) {
            throw new DAOException(pe, "persistence.error.unexpected");
        }
    }

    @Override
    @PermitAll
    public List<E> findAll(OrderBy... orderBy) throws DAOException {
        return select(null, null, null, orderBy);
    }

    @Override
    @PermitAll
    public List<E> findFiltered(Map<String, Object> filters, OrderBy... orderBy) throws DAOException {
        return select(filters, null, null, orderBy);
    }

    @Override
    @PermitAll
    public List<E> findAll(@PositiveOrZero int first, @Positive int pageSize, OrderBy... orderBy) throws DAOException {
        return select(null, first, pageSize, orderBy);
    }

    @Override
    @PermitAll
    public List<E> findFiltered(Map<String, Object> filters, @PositiveOrZero int first, @Positive int pageSize,
                                OrderBy... orderBy) throws DAOException {
        return select(filters, first, pageSize, orderBy);
    }

    @Override
    public E getReference(PK id) {
        return entityManager.getReference(entityClass, id);
    }

    // ///////////////////////////////////////////////////////////////////
    // MÈTODES INTERNS

    /**
     * Obté una llista de les entitats filtrades per una cadena de caràcters per paginacions.
     * La cadena de filtre es cerca dins tots els camps de tipus String de l'entitat.
     *
     * @param filters     map on les claus són el nom d'atribut i el valor pel qual s'ha de filtrar.
     * @param firstResult el número d'ordre de la primera entitat a tornar. La primera és 0.
     * @param maxResults  el nombre màxim d'entitats a tornar.
     * @param orderByList Camps per ordenar el llistat
     * @return llista d'entitats que compleixen el filtre.
     */
    protected List<E> select(Map<String, Object> filters, Integer firstResult, Integer maxResults,
                             OrderBy... orderByList) throws DAOException {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(entityClass);
        final Root<E> root = cq.from(entityClass);
        cq.select(root);

        if (filters != null && !filters.isEmpty()) {
            cq.where(getFilterPredicate(root, filters));
        }

        if (orderByList != null && orderByList.length > 0) {
            List<Order> orderList = new ArrayList<>();
            for (OrderBy orderBy : orderByList) {
                Path<Object> attributePath = root.get(orderBy.property);
                if (orderBy.orderType == OrderType.ASC) {
                    orderList.add(cb.asc(attributePath));
                } else {
                    orderList.add(cb.desc(attributePath));
                }
            }
            cq.orderBy(orderList);
        }

        TypedQuery<E> query = entityManager.createQuery(cq);
        if (firstResult != null) {
            query.setFirstResult(firstResult);
        }
        if (maxResults != null) {
            query.setMaxResults(maxResults);
        }

        try {
            return query.getResultList();
        } catch (PersistenceException pe) {
            throw new DAOException(pe, "persistence.error.unexpected");
        }
    }

    /**
     * Obté un predicat per emprar amb un where per aplicar uns filtres determinats.
     *
     * @param root    objecte emprant al from del select d'on s'agafen els camps.
     * @param filters Filtres a aplicar.
     * @return predicat per emprar a un where per aplicar el filtre. Si els filtres són buids, retorna
     * un predicat que sempre avalua a <code>true</code>.
     */
    @SuppressWarnings("unchecked")
    protected Predicate getFilterPredicate(Root<E> root, Map<String, Object> filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        if (filters == null || filters.isEmpty()) {
            // No imposa cap restricció, el predicat sempre avalua a true.
            return cb.conjunction();
        }

        List<Predicate> predicateList = new ArrayList<>();
        for (String attributeName : filters.keySet()) {
            @SuppressWarnings("rawtypes")
            Path attributePath = root.get(attributeName);
            @SuppressWarnings("rawtypes")
            Class bindableJavaType = attributePath.getModel().getBindableJavaType();
            Object attributeValue = filters.get(attributeName);

            // Quan el filtre és de tipus String, aplicam un like
            if (bindableJavaType.equals(String.class)) {
                predicateList.add(
                        cb.like(
                                cb.lower((Path<String>) attributePath),
                                "%" + ((String) attributeValue).toLowerCase() + "%"
                        )
                );
            // Quan és una enumeració, però rebem un String, hem de convertir el valor que rebem a enumeració
            } else if (bindableJavaType.isEnum() && (attributeValue instanceof String)) {
                @SuppressWarnings("rawtypes")
                Enum enumValue = Enum.valueOf(bindableJavaType, (String) attributeValue);
                predicateList.add(cb.equal(attributePath, enumValue));
            // En qualsevol altre cas, feim directament l'equal
            } else {
                predicateList.add(cb.equal(attributePath, attributeValue));
            }
        }
        // Retornam un AND amb el predicat de cada propietat.
        return cb.and(predicateList.toArray(Predicate[]::new));
    }
}
