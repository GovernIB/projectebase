package es.caib.projectebase.service.facade;

import es.caib.projectebase.service.exception.RecursNoTrobatException;
import es.caib.projectebase.service.exception.UnitatOrganicaDuplicadaExeption;
import es.caib.projectebase.service.model.Ordre;
import es.caib.projectebase.service.model.Page;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.Map;

/**
 * Servei per els casos d'ús de mateniment d'una Unitat Orgànica.
 *
 * @author areus
 */
public interface UnitatOrganicaServiceFacade {

    Long create(@NotNull @Valid UnitatOrganicaDTO dto) throws UnitatOrganicaDuplicadaExeption;

    void update(@NotNull @Valid UnitatOrganicaDTO dto) throws UnitatOrganicaDuplicadaExeption;

    void delete(@NotNull Long id);

    UnitatOrganicaDTO findById(@NotNull Long id) throws RecursNoTrobatException;

    Page<UnitatOrganicaDTO> findFiltered(@PositiveOrZero int firstResult, @Positive int maxResult,
            @NotNull Map<String, Object> filters, @NotNull List<Ordre> ordenacio);
}
