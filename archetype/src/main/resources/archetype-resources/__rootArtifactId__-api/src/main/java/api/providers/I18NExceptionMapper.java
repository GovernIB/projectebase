#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.providers;

import ${package}.commons.i18n.I18NException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJBException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Permet mapejar a una respota comuna a les excepcions de tipus I18NException.
 *
 * @author areus
 */
@Provider
public class I18NExceptionMapper implements ExceptionMapper<I18NException> {

    private static final Logger LOG = LoggerFactory.getLogger(I18NExceptionMapper.class);

    @Override
    public Response toResponse(I18NException e) {
        LOG.error("Rebuda una I18NException de la capa EJB: " + e.getMessage());
        LOG.error("Retornam un codi 500 al client");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
