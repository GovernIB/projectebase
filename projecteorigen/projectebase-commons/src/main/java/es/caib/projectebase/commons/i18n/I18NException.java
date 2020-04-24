package es.caib.projectebase.commons.i18n;

import java.util.Locale;

/**
 * Excepció que permet emprar missatges traduïbles.
 * Emprarà una instància de {@link I18NTranslator} per traduir els missatges.
 * Les subclasses han d'implementar el mètode {@link #getTranslator()} per indicar
 * la instància a emprar per traduir els missatges.
 */
public abstract class I18NException extends Exception {

    private static final long serialVersionUID = -3124602547590507219L;

    private Object[] parameters;

    public Object[] getParameters() {
        return parameters;
    }

    /**
     * Crea una excepció amb un missatge traduïble.
     * @param message etiqueta del missatge a traduir.
     */
    public I18NException(String message) {
        super(message);
    }

    /**
     * Crea una excepció amb un missatge traduïble i els paràmetres indicats.
     * @param message etiqueta del missatge a traduir.
     * @param parameters paràmetres del missatge.
     */
    public I18NException(String message, Object... parameters) {
        super(message);
        this.parameters = parameters;
    }

    /**
     * Crea una excepció amb un missatge traduïble.
     * @param cause excepció original
     * @param message etiqueta del missatge a traduir.
     */
    public I18NException(Throwable cause, String message) {
        super(message, cause);
    }

    /**
     * Crea una excepció amb un missatge traduïble i els paràmetres indicats.
     * @param cause excepció original
     * @param message etiqueta del missatge a traduir.
     * @param parameters paràmetres del missatge.
     */
    public I18NException(Throwable cause, String message, Object... parameters) {
        super(message, cause);
        this.parameters = parameters;
    }

    /**
     * Proporciona la instància de {@link I18NTranslator} que s'emprarà per
     * traduir els missatges.
     * @return instància per traduir els missatges.
     */
    protected abstract I18NTranslator getTranslator();

    /**
     * Retorna el missatge de l'excepció traduit en l'idioma per defecte, {@link Locale#getDefault()}.
     * @return missatge traduit.
     */
    @Override
    public String getLocalizedMessage() {
        return getLocalizedMessage(Locale.getDefault());
    }

    /**
     * Retorna el missatge de l'excepció traduit en l'idioma indicat.
     * @param locale idioma per traduir.
     * @return missatge traduit.
     */
    public String getLocalizedMessage(Locale locale) {
        return getTranslator().translate(locale, super.getMessage(), parameters);
    }
}
