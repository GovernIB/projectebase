#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.utils;

import ${package}.service.model.Ordre;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Utilitats relaconades amb Primefaces
 *
 * @author areus
 */
public final class PFUtils {

    // No permetem que s'instancii la classe només proveeix mètodes static d'utilitat
    private PFUtils() {}

    /**
     * Converteix una llista de SortMeta, l'abstracció que empra Primefaces per l'ordenaicó,
     * a la nostra abstracció: Ordre.
     */
    public static List<Ordre> sortMetaToOrdre(Collection<SortMeta> multiSortMeta) {
        if (multiSortMeta == null || multiSortMeta.isEmpty()) {
            return Collections.emptyList();
        }

        return multiSortMeta.stream().map(
                    sortMeta -> (sortMeta.getSortOrder() == SortOrder.ASCENDING ?
                            Ordre.ascendent(sortMeta.getSortField()) :
                            Ordre.descendent(sortMeta.getSortField()))
            ).collect(Collectors.toList());
    }

    public static Map<String, Object> filterMetaToFilter(Map<String, FilterMeta> filterBy) {
        if (filterBy == null || filterBy.isEmpty()) {
            return Collections.emptyMap();
        }

        return filterBy.entrySet().stream()
                .filter(e -> Objects.nonNull(e.getValue().getFilterValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().getFilterValue()
                ));
    }
}
