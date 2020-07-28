package es.caib.projectebase.back.utils;

import es.caib.projectebase.service.model.Ordre;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import java.util.Collections;
import java.util.List;
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
    public static List<Ordre> sortMetaToOrdre(List<SortMeta> multiSortMeta) {
        if (multiSortMeta == null || multiSortMeta.isEmpty()) {
            return Collections.emptyList();
        }

        return multiSortMeta.stream().map(
                    sortMeta -> (sortMeta.getSortOrder() == SortOrder.ASCENDING ?
                            Ordre.ascendent(sortMeta.getSortField()) :
                            Ordre.descendent(sortMeta.getSortField()))
            ).collect(Collectors.toList());
    }
}
