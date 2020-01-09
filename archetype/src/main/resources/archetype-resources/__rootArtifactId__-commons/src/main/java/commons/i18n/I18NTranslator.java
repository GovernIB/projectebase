#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.commons.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.ResourceBundle.Control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author anadal
 *
 */
public class I18NTranslator {

    public final Logger log = LoggerFactory.getLogger(I18NTranslator.class);

    protected final TreeMap<String, ResourceBundle> bundles = new TreeMap<String, ResourceBundle>();

    protected final String[] bundlesNames;

    protected static UTF8Control UTF8CONTROL = new UTF8Control();

    public I18NTranslator(String[] bundlesNames) {
        super();
        this.bundlesNames = bundlesNames;
    }

    public String tradueix(boolean useCodeIfNotExist, Locale loc, String code, String... args) {
        return tradueix(useCodeIfNotExist ? code : null, loc, code, args);
    }

    public String tradueix(Locale loc, String code, String... args) {
        return tradueix(null, loc, code, args);
    }

    public String tradueix(String valueIfNotExist, Locale loc, String code, String... args) {

        // Cache de resource bundle
        String msg = null;
        Exception lastException = null;

        for (String res : bundlesNames) {
            String key = res + "_" + loc.toString();
            ResourceBundle resource = bundles.get(key);
            if (resource == null) {
                resource = ResourceBundle.getBundle(res, loc, UTF8CONTROL);
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
                log.error("La clau de traducció [" + code + "] per l'idioma " + lang + " no existeix: "
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

    public static class UTF8Control extends Control {
        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader,
                boolean reload) throws IllegalAccessException, InstantiationException, IOException {
            // The below is a copy of the default implementation.
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "properties");
            ResourceBundle bundle = null;
            InputStream stream = null;
            if (reload) {
                URL url = loader.getResource(resourceName);
                if (url != null) {
                    URLConnection connection = url.openConnection();
                    if (connection != null) {
                        connection.setUseCaches(false);
                        stream = connection.getInputStream();
                    }
                }
            } else {
                stream = loader.getResourceAsStream(resourceName);
            }
            if (stream != null) {
                try {
                    // Only this line is changed to make it to read properties files as
                    // UTF-8.
                    bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
                } finally {
                    stream.close();
                }
            }
            return bundle;
        }
    }

    public String tradueix(I18NValidationException ve, Locale locale) {
        StringBuffer str = new StringBuffer();

        for (I18NFieldError fe : ve.getFieldErrorList()) {
            I18NTranslation trans = fe.getTranslation();
            String code = trans.getCode();
            String[] args = tradueixArguments(locale, trans.getArgs());
            String error = tradueix(locale, code, args);
            String field = fe.getField();
            String fieldLabel = tradueix(locale, field);

            if (str.length() != 0) {
                str.append("${symbol_escape}n");
            }
            str.append(fieldLabel + "(" + field + "): " + error);

        }
        return str.toString();
    }

    public String tradueix(I18NException e, Locale locale) {
        return tradueix(locale, e.getMessage(), tradueixArguments(locale, e.getTraduccio().getArgs()));
    }

    public String[] tradueixArguments(Locale locale, I18NArgument... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        String[] traduits = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                if (args[i] instanceof I18NArgumentCode) {
                    traduits[i] = tradueix(locale, args[i].getValue());
                } else {
                    traduits[i] = args[i].getValue();
                }
            }
        }
        return traduits;
    }
}
