#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.commons.i18n;

/**
 * Representa un paràmetre per el formateig d'un missatge que és una etiqueta que es traduirà.
 *
 * @author anadal
 */
public class I18NArgumentCode implements I18NArgument {

    private final String code;

    /**
     * Construeix un nou argument que és una etiqueta.
     *
     * @param code etiqueta.
     */
    public I18NArgumentCode(String code) {
        super();
        this.code = code;
    }

    public String getValue() {
        return this.code;
    }

}
