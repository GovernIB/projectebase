package es.caib.projectebase.api.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJBException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Permet mapejar a una respota qualsevol excepció unchecked.
 * Envia un codi d'error 500 i evita que els detalls de l'excepció arribin al client,
 * ja que un error de sistema l'ha de mirar l'administrador.
 *
 * @author areus
 */
@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

    private static final Logger LOG = LoggerFactory.getLogger(RuntimeExceptionMapper.class);

    @Override
    public Response toResponse(RuntimeException e) {
        LOG.error("Rebuda una RuntimeException: " + e.getMessage());
        LOG.error("Retornam un codi 500 al client");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
