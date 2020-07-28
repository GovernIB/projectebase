package es.caib.projectebase.service.facade;

import es.caib.projectebase.service.model.Ordre;
import es.caib.projectebase.service.model.Page;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * Servei per els casos d'ús de mateniment d'una Unitat Organica
 */
public interface UnitatOrganicaServiceFacade {

    Long create(@NotNull @Valid UnitatOrganicaDTO dto);

    void update(@NotNull @Valid UnitatOrganicaDTO dto);

    void delete(@NotNull Long id);

    UnitatOrganicaDTO findById(@NotNull Long id);

    Page<UnitatOrganicaDTO> findFiltered(@PositiveOrZero int firstResult, @Positive int maxResult, 
            @NotNull Map<String, Object> filters, @NotNull List<Ordre> ordenacio);
}
