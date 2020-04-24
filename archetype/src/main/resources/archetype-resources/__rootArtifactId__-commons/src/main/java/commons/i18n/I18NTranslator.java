#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.commons.i18n;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Permet traduir missatges indicant un nom d'etiqueta i opcionalment varis paràmetres. Els paràmetres a la seva
 * vegada poden ser etiquetes si estan entre claus, p.e. <code>{camp.nom}</code>.
 * Si no s'indica el <code>locale</code> per la trauducció agafarà {@link Locale${symbol_pound}getDefault()}.
 * <p>
 * Les intàncies s'obtenen cridant {@link ${symbol_pound}from(String...)}, passant per paràmetre noms de recursos que es carregaran
 * com a {@link ResourceBundle} per emprar com a recursos per fer la traducció.
 * </p>
 * <p>
 * Exemple d'ús:
 * Suposem que tenim un fitxer <code>Labels_ca.properties</code> amb el següent contingut:
 *  <pre>
 *      error.tamany=El camp {0} només pot tenir {1} caràcters.
 *      camp.nom=Nom
 *  </pre>
 *  Amb el codi:
 *  <pre>
 *      I18NTranslator translator = I18NTranslator.from("Labels");
 *      String message = translator.translate(locale, "error.tamany", "{camp.nom}", 20);
 *  </pre>
 *  Obtendrem el missatge: <code>El camp Nom només pot tenir 20 caràcters</code>.
 *  Si per exemle, l'etiqueta <code>error.tamany</code> la tenim a un fitxer
 *  <code>ErrorLabels_ca.properties</code> i l'etiqueta <code>camp.nom</code> a un fitxer
 *  <code>FieldLabels_ca.properties</code>, empraríem:
 *  <pre>
 *      I18NTranslator translator = I18NTranslator.from("ErrorLabels", "FieldLabels);
 *      String message = translator.translate(locale, "error.tamany", "{camp.nom}", 20);
 *  </pre>
 *  Amb idèntic resultat.
 *  </p>
 * <p>
 *  Fixau-vos que el pàmetre <code>"error.tamany"</code> no està envolutat de <code>{}</code> perquè
 *  aquest paràmetre sempre és una etiqueta que es traueix, mentre que <code>"{camp.nom}"</code> si que
 *  n'ha de dur, perquè és un paràmetre i si no en dugués el que faria seria emprar com a paràemtre el literal.
 *  P.e.
 *  <pre>
 *      String message = translator.translate(locale, "error.tamany", "camp.nom", 20);
 *  </pre>
 *  Produiria: <code>El camp camp.nom només pot tenir 20 caràcters</code>
 * </p>
 */
public class I18NTranslator implements Serializable {

    private static final long serialVersionUID = 2523058719554958402L;

    /**
     * Instància de ResourceBundle.Control emprada per carrega els bundles.
     * Atès que la instància és reutilitzable la declaram aquí perque sigui única.
     */
    private static final ResourceBundle.Control NO_FALLBACK_CONTROL = new NofallbackControl();

    /**
     * Cache d'instàncies de I18NTranslator. A cada llista de String que emprem li correspondrà una única instància.
     */
    private static final ConcurrentMap<List<String>, I18NTranslator> INSTANCES = new ConcurrentHashMap<>();

    /**
     * Caché de ResourceBundle de cada instància de I18NTranslator
     */
    private final ConcurrentMap<Locale, ResourceBundle> bundleMap = new ConcurrentHashMap<>();

    /**
     * Llista de noms de ResourceBundle que empra aquesta instància
     */
    private final List<String> bundleNamesList;

    /**
     * Construeix una instància a partir de la llista de noms de resource bundle.
     * @param bundleNamesList llista de noms de resource bundle.
     */
    private I18NTranslator(List<String> bundleNamesList) {
        this.bundleNamesList = bundleNamesList;
    }

    /**
     * Retorna una instància a partir dels noms de resource bundle indicats. Si no existeix, la crea.
     * Per cada llista de noms de resource bundle es crea una instància única.
     * @param bundleNames noms de resource bundle.
     * @return una instància que correspon als noms de resource bundle indicats.
     */
    public static I18NTranslator from(String... bundleNames) {
        if (bundleNames == null || bundleNames.length == 0) {
            throw new IllegalArgumentException("bundleNames ha de contenir al manco un element");
        }
        // Només executa el constructor si dins el map no hi ha la clau indicada
        return INSTANCES.computeIfAbsent(Arrays.asList(bundleNames), I18NTranslator::new);
    }

    /**
     * Obté el resource bundle per el locale indicat. L'agafa a partir dels noms de resource bundle
     * amb que s'ha creat la intància. Si són varis retorna un {{@link MultipleResourceBundle}.
     * @param locale locale del resource bundle.
     * @return resource bundle corresponent al locale indicat.
     */
    protected ResourceBundle getBundle(Locale locale) {
        // Només executa la funció per obtenir el ResourceBundle si no existeix ja dins el map.
        return bundleMap.computeIfAbsent(locale,
                loc -> {
                    if (bundleNamesList.size() == 1) {
                        return ResourceBundle.getBundle(bundleNamesList.get(0), loc, NO_FALLBACK_CONTROL);
                    } else {
                        List<ResourceBundle> bundles = bundleNamesList.stream()
                                .map(name -> ResourceBundle.getBundle(name, loc, NO_FALLBACK_CONTROL))
                                .collect(Collectors.toList());
                        return new MultipleResourceBundle(bundles);
                    }
                });
    }

    /**
     * Procesa un paràmetre de tipus string. Si comença i acaba amb claus <code>{}</code> i no té
     * espais ni <code>=</code> el tracte com una etiqueta i intenta traduir-ho. Sinó el retorna talment
     * com a literal.
     * @param locale idioma a emprar si cal traduir
     * @param string paràmetre string a processar
     * @return el mateix string si no necessita processar o el resultat de traduir l'etiqueta.
     */
    private String processStringParam(Locale locale, String string) {
        if (string.startsWith("{")
                && !string.contains(" ")
                && !string.contains("=")
                && string.endsWith("}")) {
            return translate(locale, string.substring(1, string.length() - 1));
        } else {
            return string;
        }
    }

    /**
     * Tradueix una etiqueta amb l'idioma, i la formateja amb els paràmetres indicats. Els
     * paràmetres de tipus <code>String</code> que estan entre claus es tradueixen prèviament
     * al seu ús com a paràmetres.
     * @param locale idioma de la traducció.
     * @param label etiqueta a traduïr
     * @param parameters paràmetres a emprar pel formateig.
     * @return etiqueta traduida, o la cadena <code>{<i>label</i>}</code> si no existeix
     * traducció.
     */
    public String translate(Locale locale, String label, Object... parameters) {
        String message;
        try {
            ResourceBundle bundle = getBundle(locale);
            message = bundle.getString(label);
            if (parameters != null && parameters.length > 0) {
                Object[] args = Arrays.stream(parameters)
                        .map(param -> param instanceof String ?
                                processStringParam(locale, (String) param) :
                                param).toArray();
                message = MessageFormat.format(message, args);
            }
        } catch (MissingResourceException e) {
            message = "{" + e.getKey() + "}";
        }
        return message;
    }

    /**
     * Tradueix l'etiqueta amb l'idioma indicat.
     * @param locale idioma de la traducció.
     * @param label etiqueta a traduïr
     * @return etiqueta traduida, o la cadena <code>{<i>label</i>}</code> si no existeix
     * traducció.
     * @see ${symbol_pound}translate(Locale, String, Object...)
     */
    public String translate(Locale locale, String label) {
        return translate(locale, label, (Object[]) null);
    }

    /**
     * Tradueix una etiqueta amb l'idioma per defecte, i la formateja amb els paràmetres indicats. Els
     * paràmetres de tipus <code>String</code> que estan entre claus es tradueixen prèviament
     * al seu ús com a paràmetres.
     * @param label etiqueta a traduïr
     * @param parameters paràmetres a emprar pel formateig.
     * @return etiqueta traduida, o la cadena <code>{<i>label</i>}</code> si no existeix
     * traducció.
     * @see ${symbol_pound}translate(Locale, String, Object...)
     */
    public String translate(String label, Object... parameters) {
        return translate(Locale.getDefault(), label, parameters);
    }

    /**
     * Tradueix l'etiqueta amb l'idioma per defecte.
     * @param label etiqueta a traduïr
     * @return etiqueta traduida, o la cadena <code>{<i>label</i>}</code> si no existeix
     * traducció.
     * @see ${symbol_pound}translate(Locale, String, Object...)
     */
    public String translate(String label) {
        return translate(Locale.getDefault(), label, (Object[]) null);
    }

    /**
     * Subclasse de Control interna emprada per carregar els ResourceBundle per que en cas
     * que no trobi el locale indicat no empri el locale per defecte.
     */
    protected static class NofallbackControl extends ResourceBundle.Control {
        @Override
        public Locale getFallbackLocale(String baseName, Locale locale) {
            return null;
        }
    }
}
