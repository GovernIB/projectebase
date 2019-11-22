package es.caib.projectebase.api.resources;

import es.caib.projectebase.jpa.UnitatOrganica;
import es.caib.projectebase.service.UnitatOrganicaService;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("unitatorganica")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UnitatOrganicaResource {

    @EJB
    private UnitatOrganicaService unitatOrganicaService;

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        UnitatOrganica unitatOrganica = unitatOrganicaService.findById(id);
        if (unitatOrganica != null) {
            return Response.ok(unitatOrganica).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response create(@Valid UnitatOrganica unitatOrganica) {
        unitatOrganicaService.create(unitatOrganica);
        return Response.created(URI.create("unitatOrganica/"+ unitatOrganica.getId())).build();
    }

    @GET
    @Path("all")
    public Response getAll() {
        List<UnitatOrganica> all = unitatOrganicaService.findAll();
        return Response.ok().entity(all).build();
    }
}
