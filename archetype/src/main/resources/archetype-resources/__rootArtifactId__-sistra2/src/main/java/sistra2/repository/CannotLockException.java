#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sistra2.repository;

import javax.ejb.ApplicationException;

@ApplicationException
public class CannotLockException extends Exception {
}
