#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.distribucio.converter;

import es.caib.distribucio.ws.backofficeintegracio.AnotacioRegistreId;
import ${package}.distribucio.persistence.AnotacioInbox;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Conversor per transformar els AnotacioRegistreId de backoffice a AnotacioInbox,
 * i de AnotacioInbox als AnotacioRegistreId de backofficeintegracio.
 *
 * Fer notar que els AnotacioRegistreId de backoffice que envia Distribució per processar, no són la mateixa
 * classe que AnotacioRegistreId de backofficeintegracio que requreix per les consultes i canvis d'estat, bàsicament
 * perquè dins els WSDL tenen namespaces diferents.
 *
 * @author areus
 */
@Mapper
public interface AnotacioInboxConverter {

    @Mapping(target="indetificador", source = "identificador")
    AnotacioRegistreId toAnotacioRegistre(AnotacioInbox anotacioInbox);

    @Mapping(target="identificador", source = "indetificador")
    AnotacioInbox toAnotacioInbox(
            ${package}.distribucio.backoffice.api.AnotacioRegistreId anotacionsRegistreId);

    List<AnotacioInbox> toAnotacioInbox(
            List<${package}.distribucio.backoffice.api.AnotacioRegistreId> anotacionsRegistre);
}
