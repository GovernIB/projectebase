package es.caib.projectebase.api.sistra2;

import es.caib.projectebase.service.model.UnitatOrganicaDTO;

import java.util.List;
import java.util.Map;

public interface UnitatConverter {

    Map<String, String> toMap(UnitatOrganicaDTO unitat);

    List<Map<String, String>> toMapList(List<UnitatOrganicaDTO> unitats);
}
