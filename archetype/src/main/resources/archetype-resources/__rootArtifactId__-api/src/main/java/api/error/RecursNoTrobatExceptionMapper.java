#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.error;

import ${package}.service.exception.RecursNoTrobatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Cas particular d'una subclasse de ServiceException que indica que no s'ha trobat un recurs.
 * En aquest cas, retornam un error 404.
 *
 * @author areus
 */
@Provider
public class RecursNoTrobatExceptionMapper implements ExceptionMapper<RecursNoTrobatException> {

    private static final Logger LOG = LoggerFactory.getLogger(RecursNoTrobatExceptionMapper.class);

    @Override
    public Response toResponse(RecursNoTrobatException recursNoTrobatException) {
        LOG.error("Rebut un error de RecursNoTrobatException");
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
