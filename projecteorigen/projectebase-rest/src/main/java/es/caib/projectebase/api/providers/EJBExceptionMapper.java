package es.caib.projectebase.api.providers;

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

    @Override
    public Response toResponse(EJBException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
