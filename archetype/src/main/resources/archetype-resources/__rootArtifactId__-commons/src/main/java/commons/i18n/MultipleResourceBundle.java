#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.commons.i18n;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Implementació d'un {@link ResourceBundle} que permet delegar, i per tant cercar etiquetes dins una llista
 * de bundles.
 *
 * @author anadal
 * @author areus
 */
public class MultipleResourceBundle extends ResourceBundle {

    private final List<ResourceBundle> delegates;

    /**
     * Construeix un nou ResourceBundle que delega en la llista de ResourceBundle indicats.
     * @param resourceBundles llista de ResourceBundle delegats. Ha de contenir al manco un element.
     */
    public MultipleResourceBundle(List<ResourceBundle> resourceBundles) {
        if (resourceBundles == null || resourceBundles.isEmpty()) {
            throw new IllegalArgumentException("resourceBundles ha de contenir al manco un element");
        }
        this.delegates = resourceBundles;
    }

    /**
     * Retorna la primera aparició de la clau dins els ResourceBundle delegats. Mira per ordre
     * en el que varen ser subministrats els ResourceBundle delegats.
     * @param key Clau a cercar
     * @return Primera aparició de la clau o null en cas contrari.
     */
    @Override
    protected Object handleGetObject(String key) {
        Optional<Object> firstPropertyValue = this.delegates.stream()
                .filter(delegate -> delegate.containsKey(key))
                .map(delegate -> delegate.getObject(key))
                .findFirst();

        return firstPropertyValue.orElse(null);
    }

    /**
     * Retorna una enumeració de totes les claus incloses dins els ResourceBundle delegats.
     * Si hi ha claus duplicades només les retorna una vegada.
     * @return enumeració de les claus dels resource bundle delegats.
     */
    @Override
    public Enumeration<String> getKeys() {
        List<String> keys = this.delegates.stream()
                .flatMap(delegate -> Collections.list(delegate.getKeys()).stream())
                .distinct()
                .collect(Collectors.toList());

        return Collections.enumeration(keys);
    }
}
