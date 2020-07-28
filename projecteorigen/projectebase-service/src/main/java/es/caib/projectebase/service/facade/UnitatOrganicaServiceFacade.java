package es.caib.projectebase.service.facade;

import es.caib.projectebase.service.model.Ordre;
import es.caib.projectebase.service.model.Page;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;

import java.util.List;
import java.util.Map;

/**
 * Servei per els casos d'ús de mateniment d'una Unitat Organica
 */
public interface UnitatOrganicaServiceFacade {

    Long create(UnitatOrganicaDTO dto);

    void update(UnitatOrganicaDTO dto);

    void delete(Long id);

    UnitatOrganicaDTO findById(Long id);

    Page<UnitatOrganicaDTO> findFiltered(int firstResult, int maxResult, Map<String, Object> filters,
                                         List<Ordre> ordenacio);
}
