#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.distribucio.repository;

import javax.ejb.ApplicationException;

/**
 * Llançat quan no es pot bloquejar una fila.
 */
@ApplicationException
public class CannotLockException extends Exception {

    private static final long serialVersionUID = 1L;
}