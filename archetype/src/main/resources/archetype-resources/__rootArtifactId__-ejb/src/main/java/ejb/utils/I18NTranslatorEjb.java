#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb.utils;

import java.util.Locale;

import ${package}.commons.i18n.I18NArgument;
import ${package}.commons.i18n.I18NException;
import ${package}.commons.i18n.I18NTranslator;
import ${package}.commons.i18n.I18NValidationException;

/**
 * 
 * @author anadal
 * 
 */
public class I18NTranslatorEjb {

    public static final I18NTranslator translator = new I18NTranslator(
            new String[] { "ValidationMessages", "persistence.LabelsPersistence", "ejb.LabelsEJB" });

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
