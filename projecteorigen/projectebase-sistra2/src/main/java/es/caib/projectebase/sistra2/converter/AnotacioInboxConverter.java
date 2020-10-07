package es.caib.projectebase.sistra2.converter;

import es.caib.distribucio.ws.backofficeintegracio.AnotacioRegistreId;
import es.caib.distribucio.ws.backofficeintegracio.Consulta;
import es.caib.projectebase.sistra2.persistence.AnotacioInbox;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Conversor per transformar els AnotacioRegistreId de backoffice a AnotacioInbox,
 * i de AnotacioInbox als AnotacioRegistreId de backofficeintegracio
 *
 * @author areus
 */
@Mapper
public interface AnotacioInboxConverter {

    @Mapping(target="indetificador", source = "identificador")
    AnotacioRegistreId toAnotacioRegistre(AnotacioInbox anotacioInbox);

    @Mapping(target="identificador", source = "indetificador")
    AnotacioInbox toAnotacioInbox(
            es.caib.projectebase.sistra2.backoffice.api.AnotacioRegistreId anotacionsRegistreId);

    List<AnotacioInbox> toAnotacioInbox(
            List<es.caib.projectebase.sistra2.backoffice.api.AnotacioRegistreId> anotacionsRegistre);
}
