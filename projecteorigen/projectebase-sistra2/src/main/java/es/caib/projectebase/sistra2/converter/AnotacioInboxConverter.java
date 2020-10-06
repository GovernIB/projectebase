package es.caib.projectebase.sistra2.converter;

import es.caib.distribucio.ws.backofficeintegracio.Consulta;
import es.caib.projectebase.sistra2.persistence.AnotacioInbox;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Conversor per transformar els AnotacioInbox a consultes d'anotacions.
 *
 * @author areus
 */
@Mapper
public interface AnotacioInboxConverter {

    @Mapping(target="id.indetificador", source = "identificador")
    @Mapping(target="id.clauAcces", source = "clauAcces")
    Consulta toConsulta(AnotacioInbox anotacioInbox);

}
