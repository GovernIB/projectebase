package es.caib.projectebase.service.model;

import java.util.Objects;

/**
 * Representa un criteri d'ordenació per una consulta.
 * Immutable.
 *
 * @param <T> enumeració que conté els camps vàlids per ordenar
 * @author areus
 */
public class Ordre<T extends Enum<T> & Atribut> {

    private final T atribut;
    private final boolean ascendent;

    private Ordre(T atribut, boolean ascendent) {
        Objects.requireNonNull(atribut, "Atribut no pot ser null");
        this.atribut = atribut;
        this.ascendent = ascendent;
    }

    public static <T extends Enum<T> & Atribut> Ordre<T> ascendent(T atribut) {
        return new Ordre<>(atribut, true);
    }

    public static <T extends Enum<T> & Atribut> Ordre<T> descendent(T atribut) {
        return new Ordre<>(atribut, false);
    }

    public T getAtribut() {
        return atribut;
    }

    public boolean isAscendent() {
        return ascendent;
    }
}
