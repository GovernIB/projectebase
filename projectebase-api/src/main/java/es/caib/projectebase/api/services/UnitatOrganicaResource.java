package es.caib.projectebase.api.services;

import es.caib.projectebase.jpa.UnitatOrganica;
import es.caib.projectebase.service.UnitatOrganicaService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Recurs REST per accedir a Unitats Organiques
 *
 * @author areus
 */
@Path("unitatorganica")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UnitatOrganicaResource {

    @EJB
    private UnitatOrganicaService unitatOrganicaService;

    /**
     * Obté una Unitat orgàncica
     *
     * @param id identificador
     * @return Resposta amb status 200 i la informació de la Unitat orgànica o
     * un resposta amb estatus 404 si l'identificador no existeix.
     */
    @GET
    @Path("{id}")
    @Operation(summary = "Obté una unitat orgànica")
    @APIResponse(description = "La unitat orgànica",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = UnitatOrganica.class)))
    @APIResponse(responseCode = "404", description = "Unitat orgànica no trobada")
    public Response get(@Parameter(description = "L'identificador de la unitat", required = true)
                            @PathParam("id") Long id) {
        UnitatOrganica unitatOrganica = unitatOrganicaService.findById(id);
        if (unitatOrganica != null) {
            return Response.ok(unitatOrganica).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Operation(summary = "Crea una nova unitat orgànica")
    @APIResponse(responseCode="201", description = "L'enllaç a la unitat orgànica creada")
    public Response create(@Valid UnitatOrganica unitatOrganica) {
        unitatOrganicaService.create(unitatOrganica);
        return Response.created(URI.create("unitatOrganica/" + unitatOrganica.getId())).build();
    }

    @GET
    @Path("all")
    public Response getAll() {
        List<UnitatOrganica> all = unitatOrganicaService.findAll();
        return Response.ok().entity(all).build();
    }
}
