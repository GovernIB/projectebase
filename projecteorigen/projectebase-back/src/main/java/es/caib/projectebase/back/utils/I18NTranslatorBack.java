package es.caib.projectebase.back.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.caib.projectebase.commons.i18n.I18NArgument;
import es.caib.projectebase.commons.i18n.I18NArgumentCode;
import es.caib.projectebase.commons.i18n.I18NException;
import es.caib.projectebase.commons.i18n.I18NFieldError;
import es.caib.projectebase.commons.i18n.I18NTranslation;
import es.caib.projectebase.commons.i18n.I18NValidationException;
import es.caib.projectebase.commons.i18n.MultipleResourceBundle;

/**
 * 
 * @author anadal
 * 
 */
public final class I18NTranslatorBack { // extends I18NCommonUtils {


    private static final Logger log = LoggerFactory.getLogger(I18NTranslatorBack.class);

    private static final Map<Locale, ResourceBundle> resourceMapping = new HashMap<Locale, ResourceBundle>();

    public static ResourceBundle getResourceBundle(Locale locale) {
        // resource = new ReloadableResourceBundleMessageSource();
        // resource.setBasename("missatges");
        // resource.setDefaultEncoding("UTF-8");

        ResourceBundle rb = resourceMapping.get(locale);

        if (rb == null) {
            FacesContext context = FacesContext.getCurrentInstance();

            String[] names = new String[] { "ValidationMessages", "labelsJPA", "labelsEJB", "labels" };

            List<ResourceBundle> bundles = new ArrayList<ResourceBundle>();
            for (String name : names) {
                ResourceBundle bundle = context.getApplication().evaluateExpressionGet(context, "#{" + name + "}",
                        ResourceBundle.class);

                bundles.add(bundle);

            }

            rb = new MultipleResourceBundle(bundles);
            resourceMapping.put(locale, rb);

        }

        return rb;

    }

    /*
     * public static String getDateTimePattern() { Locale loc =
     * LocaleContextHolder.getLocale(); return
     * i18NDateTimeFormat.getSimpleDateFormat(loc).toPattern(); }
     * 
     * public static String getDatePattern() { Locale loc = LocaleContextHolder.getLocale();
     * return i18NDateFormat.getSimpleDateFormat(loc).toPattern(); }
     * 
     * public static String getTimePattern() { Locale loc = LocaleContextHolder.getLocale();
     * return i18NTimeFormat.getSimpleDateFormat(loc).toPattern(); }
     * 
     * public static String getJSDateTimePattern() { return fixPattern(getDateTimePattern()); }
     * 
     * public static String getJSDatePattern() { return fixPattern(getDatePattern()); }
     * 
     * public static String getJSTimePattern() { return fixPattern(getTimePattern()); }
     * 
     * public static int getFirstDayOfTheWeek() { Locale loc = LocaleContextHolder.getLocale();
     * return Calendar.getInstance(loc).getFirstDayOfWeek() - 1; }
     * 
     * public static String fixPattern(String datetimepattern) { // JAVA d/MM/yyyy H:mm:ss a =>
     * PICKER dd/MM/yyyy hh:mm:ss PP // JAVA dd/M/yyyy h:mm:ss a => PICKER dd/MM/yyyy HH:mm:ss
     * PP if (datetimepattern.indexOf('h') != -1) { if (datetimepattern.indexOf("hh") != -1) {
     * datetimepattern = datetimepattern.replace("hh", "HH"); } else { datetimepattern =
     * datetimepattern.replace("h", "HH"); } } else { if (datetimepattern.indexOf('H') != -1) {
     * if (datetimepattern.indexOf("HH") != -1) { datetimepattern =
     * datetimepattern.replace("HH", "hh"); } else { datetimepattern =
     * datetimepattern.replace("H", "hh"); } } }
     * 
     * if (datetimepattern.indexOf('a') != -1) { // TODO Replace aa => a fins que no hi hagi
     * més aa datetimepattern = datetimepattern.replace("a", "PP"); }
     * 
     * if (datetimepattern.indexOf('d') != -1) { if (datetimepattern.indexOf("dd") != -1) {
     * datetimepattern = datetimepattern.replace("dd", "dd"); } else { datetimepattern =
     * datetimepattern.replace("d", "dd"); } }
     * 
     * if (datetimepattern.indexOf('M') != -1) { if (datetimepattern.indexOf("MM") != -1) {
     * datetimepattern = datetimepattern.replace("MM", "MM"); } else { datetimepattern =
     * datetimepattern.replace("M", "MM"); } }
     * 
     * return datetimepattern; }
     * 
     * public static void setMessageSource(MessageSource ms) { resource = ms; }
     */

    public static String tradueix(boolean useCodeIfNotExist, String code, String... args) {
        return tradueix(useCodeIfNotExist ? code : null, code, args);
    }

    public static String tradueix(I18NTranslation translation) {
        String code = translation.getCode();
        String[] args = tradueixArguments(translation.getArgs());
        return tradueix(null, code, args);
    }

    public static String tradueixArgs(String code, I18NArgument... args) {
        String[] argsT = tradueixArguments(args);
        return tradueix(null, code, argsT);
    }

    public static String tradueix(String code) {
        return tradueix(null, code, (String[]) null);
    }

    public static String tradueix(String code, String... args) {
        return tradueix(null, code, args);
    }

    public static String tradueix(String valueIfNotExist, String code, String... args) {

        if (code == null) {
            return null;
        }

        Locale loc = getCurrentLocale();

        ResourceBundle resource = getResourceBundle(loc);

        if (resource == null) {
            log.error("No s'ha definit l'objecte resourceBundle dins la classe " + I18NTranslatorBack.class);
            return "{" + loc.getLanguage() + "_" + code + "}";
        }
        try {
            String trans = resource.getString(code);
            if (args != null && args.length != 0) {
                trans = MessageFormat.format(trans, (Object[]) args);
            }
            return trans;

        } catch (MissingResourceException nsme) {
            if (valueIfNotExist == null) {
                log.error("No es pot obtenir la clau de traducció [" + code + "] per l'idioma " + loc.getLanguage()
                        + ": " + nsme.getMessage(), nsme);
                return "{" + loc.getLanguage() + "_" + code + "}";
            } else {
                return valueIfNotExist;
            }
        }

    }

    public static String tradueix(I18NValidationException ve) {
        StringBuffer str = new StringBuffer();

        for (I18NFieldError fe : ve.getFieldErrorList()) {
            I18NTranslation trans = fe.getTranslation();
            String code = trans.getCode();
            String[] args = tradueixArguments(trans.getArgs());
            String error = tradueix(code, args);
            String field = fe.getField();
            String fieldLabel = field; // TODO Traduir el camp

            if (str.length() != 0) {
                str.append("\n");
            }
            str.append(fieldLabel + "(" + field + "): " + error);

        }
        return str.toString();
    }

    public static String tradueix(I18NException e) {
        I18NTranslation traduccio = e.getTraduccio();
        return tradueix(traduccio.getCode(), tradueixArguments(traduccio.getArgs()));
    }

    public static String[] tradueixArguments(I18NArgument[] args) {
        if (args == null || args.length == 0) {
            return null;
        }
        String[] traduits = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                if (args[i] instanceof I18NArgumentCode) {
                    traduits[i] = tradueix(args[i].getValue(), (String[]) null);
                } else {
                    traduits[i] = args[i].getValue();
                }
            }
        }
        return traduits;
    }

    public static Locale getCurrentLocale() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot viewRoot = facesContext.getViewRoot();
        Locale locale = viewRoot.getLocale();
        return locale;
    }

    /*
     * public static String tradueix(boolean useCodeIfNotExist, String code, String... args) {
     * 
     * return tradueix(useCodeIfNotExist ? code : null, Utils.getCurrentLocale(), code, args);
     * }
     * 
     * public static String tradueix(I18NTranslation translation) { Locale loc =
     * Utils.getCurrentLocale(); String code = translation.getCode(); String[] args =
     * tradueixArguments(loc, translation.getArgs()); return tradueix(null, loc, code, args); }
     * 
     * public static String tradueix(String code, String... args) { Locale loc =
     * Utils.getCurrentLocale(); return tradueix(null, loc, code, args); }
     * 
     * public static Exception throwException(I18NException e) throws Exception { throw new
     * Exception(tradueix(e), e.getCause()); }
     * 
     * public static String tradueix(I18NValidationException ve) { return getMessage(ve,
     * Utils.getCurrentLocale()); }
     * 
     * public static String tradueix(I18NException e) { return getMessage(e,
     * Utils.getCurrentLocale()); }
     */
}
