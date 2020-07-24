package es.caib.projectebase.service.model;

import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Resultat d'una consulta paginada que agrupa la llista de resultats amb el nombre total de resultats.
 * Immutable.
 *
 * @author areus
 */
public class Page<T> extends AbstractList<T> {

    private final List<T> items;
    private final long total;

    public Page(List<T> items, long total) {
        Objects.requireNonNull(items, "items no pot ser null");
        this.items = Collections.unmodifiableList(items);
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Page{" +
                "items=" + items +
                ", total=" + total +
                '}';
    }

    @Override
    public T get(int index) {
        return items.get(index);
    }

    @Override
    public int size() {
        return items.size();
    }
}