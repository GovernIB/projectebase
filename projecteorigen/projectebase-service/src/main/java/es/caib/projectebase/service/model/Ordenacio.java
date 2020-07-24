package es.caib.projectebase.service.model;

/**
 * Representa un criteri d'ordenació per una consulta.
 * @author areus
 */
public class Ordenacio {

    private final String atribut;
    private final boolean ascendent;

    private Ordenacio(String atribut, boolean ascendent) {
        if (atribut == null || atribut.isEmpty()) {
            throw new IllegalArgumentException("Atribut invàlid: " + atribut);
        }

        this.atribut = atribut;
        this.ascendent = ascendent;
    }

    public static Ordenacio ascendent(String atribut) {
        return new Ordenacio(atribut, true);
    }

    public static Ordenacio descendent(String atribut) {
        return new Ordenacio(atribut, false);
    }

    public String getAtribut() {
        return atribut;
    }

    public boolean isAscendent() {
        return ascendent;
    }
}
