package es.caib.projectebase.front.utils;

import es.caib.projectebase.commons.i18n.I18NArgument;
import es.caib.projectebase.commons.i18n.I18NArgumentCode;
import es.caib.projectebase.commons.i18n.I18NException;
import es.caib.projectebase.commons.i18n.I18NFieldError;
import es.caib.projectebase.commons.i18n.I18NTranslation;
import es.caib.projectebase.commons.i18n.I18NValidationException;
import es.caib.projectebase.commons.i18n.MultipleResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 
 * @author anadal
 * 
 */
public final class I18NTranslatorFront { 


    private static final Logger log = LoggerFactory.getLogger(I18NTranslatorFront.class);

    private static final Map<Locale, ResourceBundle> resourceMapping = new HashMap<Locale, ResourceBundle>();

    public static ResourceBundle getResourceBundle(Locale locale) {

        ResourceBundle rb = resourceMapping.get(locale);

        if (rb == null) {
            FacesContext context = FacesContext.getCurrentInstance();

            // Identificadors de ResourceBundles que es troben a faces-config.xml
            String[] names = new String[] { "ValidationMessages", "labelsPersistence", "labelsEJB", "labels" };

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


    public static String translate(boolean useCodeIfNotExist, String code, String... args) {
        return translate(useCodeIfNotExist ? code : null, code, args);
    }

    public static String translate(I18NTranslation translation) {
        String code = translation.getCode();
        String[] args = translateArguments(translation.getArgs());
        return translate(null, code, args);
    }

    public static String translateArguments(String code, I18NArgument... args) {
        String[] argsT = translateArguments(args);
        return translate(null, code, argsT);
    }

    public static String translate(String code) {
        return translate(null, code, (String[]) null);
    }

    public static String translate(String code, String... args) {
        return translate(null, code, args);
    }

    public static String translate(String valueIfNotExist, String code, String... args) {

        if (code == null) {
            return null;
        }

        Locale loc = getCurrentLocale();

        ResourceBundle resource = getResourceBundle(loc);

        if (resource == null) {
            log.error("No s'ha definit l'objecte resourceBundle dins la classe " + I18NTranslatorFront.class);
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

    public static String translate(I18NValidationException ve) {
        StringBuffer str = new StringBuffer();

        for (I18NFieldError fe : ve.getFieldErrorList()) {
            I18NTranslation trans = fe.getTranslation();
            String code = trans.getCode();
            String[] args = translateArguments(trans.getArgs());
            String error = translate(code, args);
            String field = fe.getField();
            String fieldLabel = field; // TODO Traduir el camp

            if (str.length() != 0) {
                str.append("\n");
            }
            str.append(fieldLabel + "(" + field + "): " + error);

        }
        return str.toString();
    }

    public static String translate(I18NException e) {
        I18NTranslation traduccio = e.getTraduccio();
        return translate(traduccio.getCode(), translateArguments(traduccio.getArgs()));
    }

    public static String[] translateArguments(I18NArgument[] args) {
        if (args == null || args.length == 0) {
            return null;
        }
        String[] traduits = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                if (args[i] instanceof I18NArgumentCode) {
                    traduits[i] = translate(args[i].getValue(), (String[]) null);
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

}
