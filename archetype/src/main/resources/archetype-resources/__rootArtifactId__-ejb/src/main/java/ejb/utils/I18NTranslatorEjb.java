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
            new String[] { "ValidationMessages", "jpa.LabelsJPA", "ejb.LabelsEJB" });

    public static String tradueix(boolean useCodeIfNotExist, Locale loc, String code, String... args) {
        return translator.tradueix(useCodeIfNotExist, loc, code, args);
    }

    public static String tradueix(Locale loc, String code, String... args) {
        return translator.tradueix(loc, code, args);
    }

    public static String tradueix(String valueIfNotExist, Locale loc, String code, String... args) {

        return translator.tradueix(valueIfNotExist, loc, code, args);
    }

    public static String tradueix(I18NValidationException ve, Locale locale) {
        return translator.tradueix(ve, locale);
    }

    public static String tradueix(I18NException e, Locale locale) {
        return tradueix(locale, e.getMessage(), tradueixArguments(locale, e.getTraduccio().getArgs()));
    }

    public static String[] tradueixArguments(Locale locale, I18NArgument... args) {
        return translator.tradueixArguments(locale, args);
    }

}
