package es.caib.projectebase.ejb.converter;

public interface Converter<E, D> {

    D toDTO(E entity);

    E toEntity(D dto);

    void updateFromDTO(E entity, D dto);
}
