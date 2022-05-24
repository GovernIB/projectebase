#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.interna.distribucio;

import ${package}.service.model.UnitatOrganicaDTO;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UnitatConverterImpl implements UnitatConverter {

    @Override
    public Map<String, String> toMap(UnitatOrganicaDTO unitat) {

        if (unitat == null) {
            return null;
        }

        Map<String, String> map = new HashMap<>(5);
        map.put("id", Long.toString(unitat.getId()));
        map.put("codiDir3", unitat.getCodiDir3());
        map.put("nom", unitat.getNom());
        map.put("dataCreacio", unitat.getDataCreacio().format(DateTimeFormatter.ISO_LOCAL_DATE));
        map.put("estat", unitat.getEstat().toString());

        return map;
    }

    @Override
    public List<Map<String, String>> toMapList(List<UnitatOrganicaDTO> unitats) {
        if (unitats == null) {
            return null;
        }

        return unitats.stream()
                .map(this::toMap)
                .collect(Collectors.toList());
    }
}
