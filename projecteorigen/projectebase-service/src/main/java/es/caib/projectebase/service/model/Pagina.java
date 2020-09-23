package es.caib.projectebase.service.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Resultat d'una consulta paginada que agrupa la llista de resultats amb el nombre total de resultats.
 * Immutable.
 *
 * @author areus
 */
public class Pagina<T> {

    private final List<T> items;
    private final long total;

    public Pagina(List<T> items, long total) {
        Objects.requireNonNull(items, "items no pot ser null");
        this.items = Collections.unmodifiableList(items);
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public List<T> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Pagina{" +
                "items=" + items +
                ", total=" + total +
                '}';
    }
}