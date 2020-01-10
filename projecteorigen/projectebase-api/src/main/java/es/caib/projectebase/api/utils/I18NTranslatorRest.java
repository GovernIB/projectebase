package es.caib.projectebase.api.utils;

import es.caib.projectebase.commons.i18n.I18NArgument;
import es.caib.projectebase.commons.i18n.I18NException;
import es.caib.projectebase.commons.i18n.I18NTranslator;
import es.caib.projectebase.commons.i18n.I18NValidationException;

import java.util.Locale;

/**
 * 
 * @author anadal
 * 
 */
public class I18NTranslatorRest {

    public static final I18NTranslator translator = new I18NTranslator(
            new String[] { "ValidationMessages", "jpa.LabelsJPA", "ejb.LabelsEJB" });

    public static String translate(boolean useCodeIfNotExist, Locale loc, String code, String... args) {
        return translator.translate(useCodeIfNotExist, loc, code, args);
    }

    public static String translate(Locale loc, String code, String... args) {
        return translator.translate(loc, code, args);
    }

    public static String translate(String valueIfNotExist, Locale loc, String code, String... args) {

        return translator.translate(valueIfNotExist, loc, code, args);
    }

    public static String translate(I18NValidationException ve, Locale locale) {
        return translator.translate(ve, locale);
    }

    public static String translate(I18NException e, Locale locale) {
        return translate(locale, e.getMessage(), translateArguments(locale, e.getTraduccio().getArgs()));
    }

    public static String[] translateArguments(Locale locale, I18NArgument... args) {
        return translator.translateArguments(locale, args);
    }

}
