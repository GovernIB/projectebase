package es.caib.projectebase.ejb.repository;

public interface CrudRepository<E, PK> {

    void create(E entity);

    void update(E entity);

    E findById(PK id);

    E getReference(PK id);

    void delete(E entity);
}
