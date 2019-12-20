package es.caib.projectebase.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.Set;

/**
 * Implementació bàsica d'un {@link DAO}.
 * @author areus
 * @param <K> Tipus de la clau primària de l'entitat.
 * @param <E> Tipus de l'entitat.
 */
public abstract class GenericJpaDAO<K, E> implements DAO<K, E> {

    /**
     * Gestor d'entitats de JPA.
     */
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * Guarda la classe del tipus d'entitat.
     */
    private final Class<E> entityClass;

    /**
     * Construtor que emmagatzema el tipus d'entitat.
     */
    protected GenericJpaDAO(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Crea una nova entitat.
     * @param entity Entitat a guardar.
     * @return l'entitat de guardar-la
     */
    @Override
    public E create(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    /**
     * Actualitza una entitat.
     * @param entity Entitat a actualitzar.
     * @return l'entitat actualitzada.
     */
    @Override
    public E update(E entity) {
        return entityManager.merge(entity);
    }

    /**
     * Esborra una entitat.
     * @param entity l'entitat a esborrar.
     */
    @Override
    public void delete(E entity) {
        entityManager.remove(entity);
    }

    /**
     * Esborra una entitat pel seu identificador.
     * @param id Identificador de l'entitat a esborrar.
     */
    @Override
    public void deleteById(K id) {
        delete(getReference(id));
    }

    /**
     * Obté una entitat en forma de referència, per tant sense carregar les seves dades.
     * Emprat bàsicament per fixar claus foranes.
     *
     * @param id Identificador de l'entitat a carregar.
     * @return L'entitat en forma de referència.
     */
    @Override
    public E getReference(K id) {
        return entityManager.getReference(entityClass, id);
    }

    /**
     * Carrega una entitat pel seu identificador.
     * @param id Identificador de l'entitat a carregar.
     * @return l'entitat que es correspon a l'identificador o null si no existeix.
     */
    @Override
    public E findById(K id) {
        return entityManager.find(entityClass, id);
    }

    /**
     * Carrega totes les entitats.
     * @return llista de totes les entitats.
     */
    @Override
    public List<E> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(entityClass);
        cq.select(cq.from(entityClass));
        TypedQuery<E> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getResultList();
    }

    /**
     * Carrega un subconjunt de les entitats.
     * @param firstResult índex del primer resultat.
     * @param size nombre màxim de resultats.
     * @return llista d'entitats.
     */
    @Override
    public List<E> findAll(@PositiveOrZero int firstResult, @Positive int size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(entityClass);
        cq.select(cq.from(entityClass));
        TypedQuery<E> typedQuery = entityManager.createQuery(cq);
        typedQuery.setFirstResult(firstResult);
        typedQuery.setMaxResults(size);
        return typedQuery.getResultList();
    }

    /**
     * Retorna el nombre d'entitats.
     * @return Nombre d'entitats.
     */
    @Override
    public long countAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(entityClass)));
        TypedQuery<Long> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getSingleResult();
    }

    /**
     * Obté una llista de les entitats filtrades per una cadena de caràcters per paginacions.
     * La cadena de filtre es cerca dins tots els camps de tipus String de l'entitat.
     *
     * @param filter   cadena de caràcters que es cercarà dins els camps de tipus string.
     * @param first    el número d'ordre de la primera entitat a tornar. La primera és 0.
     * @param pageSize el nombre màxim d'entitats a tornar.
     * @return llista d'entitats que compleixen el filtre.
     */
    public List<E> findFiltered(String filter, @PositiveOrZero int first, @Positive int pageSize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(entityClass);
        final Root<E> root = cq.from(entityClass);
        cq.select(root);
        cq.where(getFilterPredicate(root, filter));

        TypedQuery<E> query = entityManager.createQuery(cq);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    /**
     * Retorna el nombre total d'entitats que compleixen el filtre indicat.
     * La cadena de filtre es cerca dins tots els camps de tipus String de l'entitat.
     *
     * @param filter   cadena de caràcters que es cercarà dins els camps de tipus string.
     * @return nombre d'entitats que compleixen el filtre.
     */
    public long countFiltered(String filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<E> root = cq.from(entityClass);
        cq.select(cb.count(root));
        cq.where(getFilterPredicate(root, filter));

        TypedQuery<Long> query = entityManager.createQuery(cq);
        return query.getSingleResult();
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
}
