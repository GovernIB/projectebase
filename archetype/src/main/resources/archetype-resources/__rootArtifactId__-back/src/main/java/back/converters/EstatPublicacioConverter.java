#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.converters;

import ${package}.service.model.EstatPublicacio;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

/**
 * Per transformar paràmetres que han de retornar enumeracions del tipus {@link EstatPublicacio}.
 * Normalment JSF ja fa la conversió que toca quan el camp mapejat es de tipus enumeració.
 * Però si mapejam a un Map (com en el cas dels filtres d'una cerca), cal que apliquem expressament
 * aquest conversor si volem rebre un enum enlloc d'un string.
 *
 * @author areus
 */
@FacesConverter(value="estatPublicacioConverter")
public class EstatPublicacioConverter extends EnumConverter {

    public EstatPublicacioConverter() {
        super(EstatPublicacio.class);
    }
}
