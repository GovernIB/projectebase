#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.error;

/**
 * Bean per modelar un error de client a l'API REST
 *
 * @author areus
 */
public class ErrorBean {

    private final String message;
    private final ErrorType type;

    public ErrorBean(String message, ErrorType type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public ErrorType getType() {
        return type;
    }
}
