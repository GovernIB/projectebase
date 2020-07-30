#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.model;

/**
 * Representa un criteri d'ordenació per una consulta.
 *
 * @author areus
 */
public class Ordre {

    private final String atribut;
    private final boolean ascendent;

    private Ordre(String atribut, boolean ascendent) {
        if (atribut == null || atribut.isEmpty()) {
            throw new IllegalArgumentException("Atribut invàlid: " + atribut);
        }

        this.atribut = atribut;
        this.ascendent = ascendent;
    }

    public static Ordre ascendent(String atribut) {
        return new Ordre(atribut, true);
    }

    public static Ordre descendent(String atribut) {
        return new Ordre(atribut, false);
    }

    public String getAtribut() {
        return atribut;
    }

    public boolean isAscendent() {
        return ascendent;
    }
}
