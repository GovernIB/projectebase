package es.caib.projectebase.ejb.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class QueryTemplateBuilder<E, R> {

    private final EntityManager entityManager;
    private final CriteriaBuilder builder;
    private final Class<E> entityClass;
    private final Class<R> resultClass;

    private final CriteriaQuery<R> criteriaQuery;
    private final Root<E> root;

    public QueryTemplateBuilder(EntityManager entityManager, Class<E> entityClass, Class<R> resultClass) {
        this.entityManager = entityManager;
        this.builder = entityManager.getCriteriaBuilder();
        this.entityClass = entityClass;
        this.resultClass = resultClass;
        criteriaQuery = builder.createQuery(resultClass);
        root = criteriaQuery.from(this.entityClass);
    }

    @SafeVarargs
    public final QueryTemplateBuilder<E, R> selectConstruct(SingularAttribute<E, ?>... camps) {
        Path<?>[] paths = Arrays.stream(camps).map(root::get).toArray(Path<?>[]::new);
        criteriaQuery.select(builder.construct(resultClass, paths));
        return this;
    }

    public QueryTemplateBuilder<E, R> selectCount() {
        if (resultClass != Long.class) {
            throw new IllegalStateException("selectCount només es pot emprar si el resultat és un Long");
        }
        // Es pot fer el cast ja que garantim que R (resultClass) és un Long
        @SuppressWarnings("unchecked")
        Selection<R> count = (Selection<R>) builder.count(root);
        criteriaQuery.select(count);
        return this;
    }

    public QueryTemplateBuilder<E, R> withParams(Map<String, Object> parameters) {
        if (!parameters.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            parameters.forEach(
                    (key, value) -> predicates.add(getPredicate(key, value))
            );
            criteriaQuery.where(predicates.toArray(Predicate[]::new));
        }
        return this;
    }

    private Predicate getPredicate(String key, Object value) {
        Path<?> path = root.get(key);
        if (path.getJavaType() == String.class){
            return builder.like( (Path<String>) path, "%" + value + "%");
        } else {
            return builder.equal(path, value);
        }

    }


    public R getSingleResult() {
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public List<R> getAllResults() {
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<R> getPagedResult(int firstResult, int maxResults) {
        TypedQuery<R> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
}
