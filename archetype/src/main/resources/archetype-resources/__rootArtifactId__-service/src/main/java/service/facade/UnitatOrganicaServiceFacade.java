#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.facade;

import ${package}.service.exception.RecursNoTrobatException;
import ${package}.service.exception.UnitatOrganicaDuplicadaExeption;
import ${package}.service.model.Ordre;
import ${package}.service.model.Page;
import ${package}.service.model.UnitatOrganicaDTO;

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
