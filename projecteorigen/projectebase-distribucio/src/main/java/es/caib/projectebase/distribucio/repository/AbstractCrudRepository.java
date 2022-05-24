package es.caib.projectebase.distribucio.repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.LockTimeoutException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * Implementació bàsica d'un {@link CrudRepository}.
 *
 * @param <E>  Tipus de l'entitat.
 * @param <PK> Tipus de la clau primària de l'entitat.
 *
 * @author areus
 */
public abstract class AbstractCrudRepository<E, PK> implements CrudRepository<E, PK> {

    @PersistenceContext(unitName = "projectebaseDistribucioPU")
    protected EntityManager entityManager;

    // ha de coincidir amb el valor fixat a hibernate.jdbc.batch_size al persistence.xml
    private static final int BATCH_SIZE = 50;

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
    public void createBulk(List<E> entityList) {
        int listSize = entityList.size();
        for (int i = 0; i < listSize; i++) {
            if (i > 0 && i % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
            entityManager.persist(entityList.get(i));
        }
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
    public E findById(PK id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public E lockById(PK id) throws CannotLockException {
        try {
            return entityManager.find(entityClass, id, LockModeType.PESSIMISTIC_WRITE,
                    Collections.singletonMap("javax.persistence.lock.timeout", 0));
        } catch (LockTimeoutException e) {
            // LockTimeoutException hereda de RuntimeException i per tant una system exception que
            // produiria una EJBExcepiton i un rollback si la llançassim directament. Per això la rellançam com
            // una excepció nova definica com a d'aplicació que no provoca rollback.
            throw new CannotLockException();
        }
    }

    @Override
    public E getReference(PK id) {
        return entityManager.getReference(entityClass, id);
    }
}
