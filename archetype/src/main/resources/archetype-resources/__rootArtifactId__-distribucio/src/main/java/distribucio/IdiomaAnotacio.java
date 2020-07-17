#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.distribucio;

/**
 * Mapeja els codis d'idioma emprat per l'API amb una enumeració
 */
public enum IdiomaAnotacio {

    CATALA("1", "Català"),
    CASTELLA("2", "Castellano");

    private final String codi;
    private final String label;

    IdiomaAnotacio(String codi, String label) {
        this.codi = codi;
        this.label = label;
    }

    public String getCodi() {
        return codi;
    }

    public String getLabel() {
        return label;
    }
}
