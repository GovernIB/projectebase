package es.caib.projectebase.commons.rest.error;

import es.caib.projectebase.commons.rest.RestConstants;
import es.caib.projectebase.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Locale;

/**
 * Permet mapejar a una resposta comuna a les excepcions de tipus ServiceException.
 * Són bàsicament excepcions a la lògica de l'aplicació, i per tant enviarem un error
 * 400 al client.
 *
 * @author areus
 */
@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceExceptionMapper.class);

    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(ServiceException serviceException) {
        Locale locale = (Locale) request.getAttribute(RestConstants.REQUEST_LOCALE);
        String message = serviceException.getLocalizedMessage(locale);
        LOG.error("Rebuda una ServiceException: {}", message);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ErrorBean.errorAplicacio(message))
                .build();
    }
}
