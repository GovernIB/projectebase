package es.caib.projectebase.api.resources;

import es.caib.projectebase.jpa.FooEntity;
import es.caib.projectebase.service.FooServiceInterface;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/foo")
public class FooResource {

    @EJB
    private FooServiceInterface fooService;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        try {
            return Response.status(200).entity(fooService.list()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }


    @POST
    @Path("/add/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFooEntity(@PathParam("value") String value) {
        try {
            FooEntity fooEntity = new FooEntity();
            fooEntity.setValue(value);
            return Response.status(200).entity(fooService.addFooEntity(fooEntity)).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
