package es.caib.projectebase.ejb.converter;

import es.caib.projectebase.persistence.model.Procediment;
import es.caib.projectebase.service.model.ProcedimentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Conversor entre Procediment i ProcedimentDTO. La implementació es generarà automàticament per MapStruct
 *
 * @author areus
 */
@Mapper
public interface ProcedimentConverter extends Converter<Procediment, ProcedimentDTO> {

    // Els camps que no tenen exactament el mateix nom s'han de mapejar. En aquest cas, només quan
    // passam de Entity a DTO ens interessa agafar la clau forana de l'unitatOrganica.
    @Mapping(target = "idUnitat", source = "unitatOrganica.id")
    @Override
    ProcedimentDTO toDTO(Procediment entity);

    @Mapping(target = "unitatOrganica", ignore = true)
    @Override
    Procediment toEntity(ProcedimentDTO dto);

    @Mapping(target = "unitatOrganica", ignore = true)
    @Override
    void updateFromDTO(@MappingTarget Procediment entity, ProcedimentDTO dto);
}
