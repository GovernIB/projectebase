#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.commons.i18n;

import java.util.Arrays;

/**
 * Representa un missatge traduïble que està compost per una etiqueta i uns arguments opcionals.
 *
 * @author anadal
 */
public class I18NTranslation {

    private final String code;
    private final I18NArgument[] args;

    /**
     * Construeix una traducció amb una etiqueta i uns arguments.
     *
     * @param code etiqueta del missatge
     * @param args arguments a emprar amb l'etiqueta
     */
    public I18NTranslation(String code, I18NArgument... args) {
        this.code = code;
        this.args = args;
    }

    /**
     * Construeix una traducció amb una etiqueta.
     *
     * @param code etiqueta del missatge
     */
    public I18NTranslation(String code) {
        this.code = code;
        this.args = null;
    }

    /**
     * Construeix una traducció amb una etiqueta i uns arguments.
     *
     * @param code etiqueta del missatge
     * @param args arguments a emprar amb l'etiqueta
     */
    public I18NTranslation(String code, String... args) {
        this.code = code;
        this.args = Arrays.stream(args).map(I18NArgumentString::new).toArray(I18NArgument[]::new);
    }

    public String getCode() {
        return code;
    }

    public I18NArgument[] getArgs() {
        return args;
    }
}
