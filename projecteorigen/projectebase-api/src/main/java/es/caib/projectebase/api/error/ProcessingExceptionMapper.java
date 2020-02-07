package es.caib.projectebase.api.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Mapeja la responsa a una excepció de validació.
 *
 * @author areus
 */
@Provider
public class ProcessingExceptionMapper implements ExceptionMapper<ProcessingException> {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessingExceptionMapper.class);

    @Override
    public Response toResponse(ProcessingException e) {
        LOG.error("Rebuda una ProcessingException: " + e.getMessage());

        ErrorBean errorBean = new ErrorBean();
        errorBean.setType(ErrorType.PETICIO);
        errorBean.setMessage(e.getMessage());

        return Response.status(Response.Status.BAD_REQUEST).entity(errorBean).build();
    }
}
