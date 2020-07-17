package es.caib.projectebase.distribucio;

/**
 * Mapeja els tipus d'anotació emprat per l'API amb una enumeració
 */
public enum TipusAnotacio {

    ENTRADA("E"),
    SORTIDA("S");

    private final String codi;

    TipusAnotacio(String codi) {
        this.codi = codi;
    }

    public String getCodi() {
        return codi;
    }
}
