package es.caib.projectebase.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

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
        delete(entityManager.getReference(entityClass, id));
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
     * @return llista de totes les entitats.
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

}
