package es.caib.projectebase.service.facade;

import es.caib.projectebase.service.exception.UnitatOrganicaDuplicadaException;
import es.caib.projectebase.service.model.Ordre;
import es.caib.projectebase.service.model.Pagina;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Servei per els casos d'ús de mateniment d'una Unitat Orgànica.
 *
 * @author areus
 */
public interface UnitatOrganicaServiceFacade {

    Long create(UnitatOrganicaDTO dto) throws UnitatOrganicaDuplicadaException;

    void update(UnitatOrganicaDTO dto);

    void delete(Long id);

    Optional<UnitatOrganicaDTO> findById(Long id);

    Pagina<UnitatOrganicaDTO> findFiltered(int firstResult, int maxResult,
                                           Map<String, Object> filters, List<Ordre> ordenacio);
}
