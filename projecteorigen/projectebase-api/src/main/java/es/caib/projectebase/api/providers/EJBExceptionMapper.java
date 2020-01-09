package es.caib.projectebase.api.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJBException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Permet mapejar a una respota qualsevol excepció unchecked de la capa d'EJBs.
 * Evita que els detalls de l'excepció arribin al client.
 *
 * @author areus
 */
@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {

    private static final Logger LOG = LoggerFactory.getLogger(EJBExceptionMapper.class);

    @Override
    public Response toResponse(EJBException e) {
        LOG.error("Rebuda una EJBException a la capa REST: " + e.getMessage());
        LOG.error("Retornam un codi 500 al client");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
