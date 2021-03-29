#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.utils;

import ${package}.service.model.Atribut;
import ${package}.service.model.Ordre;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Utilitats relacionades amb Primefaces
 *
 * @author areus
 */
public final class PFUtils {

    // No permetem que s'instancii la classe només proveeix mètodes static d'utilitat
    private PFUtils() {}

    /**
     * Converteix una llista de SortMeta, l'abstracció que empra Primefaces per l'ordenaicó,
     * a la nostra abstracció: Ordre.
     * Caldrà que el nom del camp dins jsf es correspongui amb el valor de l'enumeració de l'Atribut.
     */
    public static <T extends Enum<T> & Atribut> List<Ordre<T>> sortMetaToOrdre(Class<T> type,
                                                                               Collection<SortMeta> multiSortMeta) {
        if (multiSortMeta == null || multiSortMeta.isEmpty()) {
            return Collections.emptyList();
        }

        return multiSortMeta.stream().map(
                    sortMeta -> (sortMeta.getSortOrder() == SortOrder.ASCENDING ?
                            Ordre.ascendent(Enum.valueOf(type, sortMeta.getSortField())) :
                            Ordre.descendent(Enum.valueOf(type, sortMeta.getSortField())))
            ).collect(Collectors.toList());
    }

    /**
     * Converteix una map de FilterMeta, l'abstracció que empra Primefaces per el filtratge, amb un map d'atributs.
     * Caldrà que el nom del camp dins jsf es correspongui amb el valor de l'enumeració de l'Atribut.
     */
    public static <T extends Enum<T> & Atribut> Map<T, Object> filterMetaToFilter(Class<T> type,
                                                                                  Map<String, FilterMeta> filterBy) {
        if (filterBy == null || filterBy.isEmpty()) {
            return Collections.emptyMap();
        }

        return filterBy.entrySet().stream()
                .filter(e -> Objects.nonNull(e.getValue().getFilterValue()))
                .collect(Collectors.toMap(
                        e -> Enum.valueOf(type, e.getKey()),
                        e -> e.getValue().getFilterValue()
                ));
    }
}
