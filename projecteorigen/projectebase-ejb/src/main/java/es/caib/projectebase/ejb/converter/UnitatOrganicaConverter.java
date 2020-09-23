package es.caib.projectebase.ejb.converter;

import es.caib.projectebase.persistence.model.UnitatOrganica;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Conversor entre UnitatOrganica i UnitatOrganicaDTO. La implementació es generarà automàticament per MapStruct
 *
 * @author areus
 */
@Mapper
public interface UnitatOrganicaConverter extends Converter<UnitatOrganica, UnitatOrganicaDTO> {

    @Override
    UnitatOrganicaDTO toDTO(UnitatOrganica entity);

    @Override
    UnitatOrganica toEntity(UnitatOrganicaDTO dto);

    @Mapping(target = "codiDir3", ignore = true) // no volem que s'actualitzi
    @Override
    void updateFromDTO(@MappingTarget UnitatOrganica entity, UnitatOrganicaDTO dto);
}
