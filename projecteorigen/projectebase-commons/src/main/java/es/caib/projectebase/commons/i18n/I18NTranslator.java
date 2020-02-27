package es.caib.projectebase.commons.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TreeMap;

/**
 * Classe d'utilitat per generar missatges a partir de traduccions.
 * @author anadal
 */
public class I18NTranslator {

    private static final Logger LOG = LoggerFactory.getLogger(I18NTranslator.class);

    protected final TreeMap<String, ResourceBundle> bundles = new TreeMap<>();
    protected final String[] bundlesNames;

    public I18NTranslator(String[] bundlesNames) {
        this.bundlesNames = bundlesNames;
    }

    public String translate(boolean useCodeIfNotExist, Locale loc, String code, String... args) {
        return translate(useCodeIfNotExist ? code : null, loc, code, args);
    }

    public String translate(Locale loc, String code, String... args) {
        return translate(null, loc, code, args);
    }

    public String translate(String valueIfNotExist, Locale loc, String code, String... args) {
        // Cache de resource bundle
        String msg = null;
        Exception lastException = null;

        for (String res : bundlesNames) {
            String key = res + "_" + loc.toString();
            ResourceBundle resource = bundles.get(key);
            if (resource == null) {
                resource = ResourceBundle.getBundle(res, loc);
                bundles.put(key, resource);
            }

            try {
                msg = resource.getString(code);
                break;
            } catch (Exception mre) {
                lastException = mre;
                continue;
            }
        }

        if (msg == null) {
            if (valueIfNotExist == null) {
                String lang = loc.toString().toUpperCase();
                if (lastException == null) {
                    lastException = new Exception();
                }
                LOG.error("La clau de traducció [" + code + "] per l'idioma " + lang + " no existeix: "
                        + lastException.getMessage(), lastException);
                return "{" + lang + "_" + code + "}";
            } else {
                return valueIfNotExist;
            }
        } else {
            if (args != null && args.length != 0) {
                msg = MessageFormat.format(msg, (Object[]) args);
            }
            return msg;
        }
    }

    public String translate(I18NException e, Locale locale) {
        return translate(locale, e.getMessage(), translateArguments(locale, e.getTraduccio().getArgs()));
    }

    public String[] translateArguments(Locale locale, I18NArgument... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        String[] traduits = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                if (args[i] instanceof I18NArgumentCode) {
                    traduits[i] = translate(locale, args[i].getValue());
                } else {
                    traduits[i] = args[i].getValue();
                }
            }
        }
        return traduits;
    }
}
