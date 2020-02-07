package es.caib.projectebase.api.error;

/**
 * Tipus possibles d'error
 *
 * @author areus
 */
public enum ErrorType {
    /**
     * Error de les regles lògiques de l'aplicació
     */
    APLICACIO,

    /**
     * Error a la validació de paràmetres
     */
    VALIDACIO,

    /**
     * Error al format de la petició
     */
    PETICIO
}