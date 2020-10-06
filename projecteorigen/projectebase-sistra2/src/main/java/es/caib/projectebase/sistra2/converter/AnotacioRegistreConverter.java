package es.caib.projectebase.sistra2.converter;

import es.caib.projectebase.sistra2.backoffice.api.AnotacioRegistreId;
import es.caib.projectebase.sistra2.persistence.AnotacioInbox;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Conversor entre les AnotacioRegistreId rebudes
 *
 * @author areus
 */
@Mapper
public interface AnotacioRegistreConverter {

    @Mapping(target="identificador", source = "indetificador")
    AnotacioInbox toAnotacioInbox(AnotacioRegistreId anotacionsRegistreId);

    List<AnotacioInbox> toAnotacioInbox(List<AnotacioRegistreId> anotacionsRegistre);

}
