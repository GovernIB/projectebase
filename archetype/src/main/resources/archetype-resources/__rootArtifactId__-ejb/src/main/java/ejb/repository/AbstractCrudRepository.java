#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * Implementació bàsica d'un {@link CrudRepository}.
 *
 * @param <E>  Tipus de l'entitat.
 * @param <PK> Tipus de la clau primària de l'entitat.
 *
 * @author areus
 */
public abstract class AbstractCrudRepository<E, PK> implements CrudRepository<E, PK> {

    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * Guarda la classe del tipus d'entitat.
     */
    private final Class<E> entityClass;

    /**
     * Emmagatzema el tipus d'entitat.
     */
    protected AbstractCrudRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void create(E entity) {
        entityManager.persist(entity);
        entityManager.flush();
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    @Override
    public void delete(E entity) {
        entityManager.remove(entity);
        entityManager.flush();
    }

    @Override
    public Optional<E> findById(PK id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public E getReference(PK id) {
        return entityManager.getReference(entityClass, id);
    }
}
