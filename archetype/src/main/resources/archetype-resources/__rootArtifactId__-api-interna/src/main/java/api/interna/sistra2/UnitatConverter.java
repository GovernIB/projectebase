#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.interna.sistra2;

import ${package}.service.model.UnitatOrganicaDTO;

import java.util.List;
import java.util.Map;

public interface UnitatConverter {

    Map<String, String> toMap(UnitatOrganicaDTO unitat);

    List<Map<String, String>> toMapList(List<UnitatOrganicaDTO> unitats);
}
