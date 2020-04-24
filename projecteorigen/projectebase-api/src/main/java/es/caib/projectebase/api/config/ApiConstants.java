package es.caib.projectebase.api.config;

/**
 * Constants emprades a l'API REST
 *
 * @author areus
 */
public interface ApiConstants {

    /**
     * Propietat del request per emmagatzemar el locale actual de la petició.
     */
    String REQUEST_LOCALE = "es.caib.projectebase.api.requestLocale";

    /**
     * Paràmetre del context definit al web.xml amb la llista de locales soportats.
     */
    String SUPPORTED_LOCALES = "es.caib.projectebase.api.supportedLocales";
}