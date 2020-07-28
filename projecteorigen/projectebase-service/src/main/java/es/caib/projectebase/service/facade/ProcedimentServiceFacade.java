package es.caib.projectebase.service.facade;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import es.caib.projectebase.service.model.Page;
import es.caib.projectebase.service.model.ProcedimentDTO;

/**
 * Servei per els casos d'ús de mateniment d'una Unitat Organica
 */
public interface ProcedimentServiceFacade {

    Long create(@NotNull @Valid ProcedimentDTO dto, @NotNull Long idUnitat);

    void update(@NotNull @Valid ProcedimentDTO dto);

    void delete(@NotNull Long id);

    ProcedimentDTO findById(@NotNull Long id);

    Page<ProcedimentDTO> findByUnitat(@PositiveOrZero int firstResult, @Positive int maxResult, @NotNull Long idUnitat);
}
