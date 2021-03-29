#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.interna.services;

import ${package}.service.facade.UnitatOrganicaServiceFacade;
import ${package}.service.model.Pagina;
import ${package}.service.model.PaginaUnitatOrganica;
import ${package}.service.model.UnitatOrganicaDTO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collections;

/**
 * Recurs REST per accedir a Unitats Organiques.
 *
 * Es pot establir la seguretat a nivell url-pattern/http-method a dins web.xml, però d'altra banda, aquesta
 * ja està establerta a nivell de la capa de serveis.
 *
 * @author areus
 */
@Path("unitats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UnitatOrganicaResource {

    @EJB
    private UnitatOrganicaServiceFacade unitatOrganicaService;

    /**
     * Retorna unta llista paginada de les unitats orgàniques.
     *
     * @return Un codi 200 amb les unitats orgàniques.
     */
    @GET
    @Operation(operationId = "getUnitats", summary = "Retorna una llista paginada d'unitats orgàniques")
    @APIResponse(
            responseCode = "200",
            description = "Llista d'unitats orgàniques",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PaginaUnitatOrganica.class)))
    public Response getUnitats(
            @Parameter(description = "Primer resultat, per defecte 0")
            @DefaultValue("0") @QueryParam("firstResult") int firstResult,
            @Parameter(description = "Nombre màxim de resultats, per defecte 10")
            @DefaultValue("10") @QueryParam("maxResult") int maxResult)  {
        Pagina<UnitatOrganicaDTO> pagina = unitatOrganicaService.findFiltered(firstResult, maxResult,
                Collections.emptyMap(), Collections.emptyList());
        return Response.ok().entity(pagina).build();
    }

    /**
     * Obté una Unitat orgàncica.
     *
     * @param id identificador
     * @return Resposta amb status 200 i la informació de la Unitat orgànica o
     * un resposta amb estatus 404 si l'identificador no existeix.
     */
    @GET
    @Path("{id}")
    @Operation(operationId = "getUnitat", summary = "Obté una unitat orgànica")
    @APIResponse(responseCode = "200",
            description = "Unitat orgànica",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UnitatOrganicaDTO.class)))
    @APIResponse(responseCode = "404", description = "Recurs no trobat")
    public Response get(@Parameter(description = "L'identificador de la unitat", required = true)
                        @PathParam("id") Long id) {
        UnitatOrganicaDTO unitatOrganica = unitatOrganicaService.findById(id)
                .orElseThrow(NotFoundException::new);
        return Response.ok(unitatOrganica).build();
    }

    /**
     * Crea una nova unitat orgànica.
     *
     * @param unitatOrganica la nova unitat orgànica a crear.
     * @return Un codi 201 amb la localització de la unitat orgància creada.
     */
    @POST
    @Operation(operationId = "createUnitat", summary = "Crea una nova unitat orgànica")
    @APIResponse(responseCode = "201", description = "Recurs creat",
            headers = @Header(name = "location", description = "Enllaç al nou recurs",
                    schema = @Schema(type = SchemaType.STRING)))
    public Response create(
            @RequestBody(
                    required = true,
                    description = "Unitat orgànica",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnitatOrganicaDTO.class)))
            @NotNull @Valid UnitatOrganicaDTO unitatOrganica) {
        Long newId = unitatOrganicaService.create(unitatOrganica);
        return Response.created(URI.create("unitats/" + newId)).build();
    }

    /**
     * Actualitza una unitat orgànica.
     *
     * @param unitatOrganica dades de la unitat orgànica a actualitzar.
     * @param id             Identificador de la unitat orgància a actualitzar.
     * @return Resposta amb status 204 si l'operació té èxit, o 404 si el recurs amb l'id indicat no existeix.
     */
    @PUT
    @Path("{id}")
    @Operation(operationId = "updateUnitat", summary = "Actualitza una unitat orgànica")
    @APIResponse(responseCode = "204", description = "Operació realitzada correctament")
    @APIResponse(responseCode = "404", description = "Recurs no trobat")
    public Response update(
            @RequestBody(
                    required = true,
                    description = "Unitat orgànica",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnitatOrganicaDTO.class)))
            @NotNull @Valid UnitatOrganicaDTO unitatOrganica,
            @Parameter(description = "L'identificador de la unitat", required = true)
            @PathParam("id") Long id) {
        unitatOrganica.setId(id);
        unitatOrganicaService.update(unitatOrganica);
        return Response.noContent().build();
    }

    /**
     * Esborra una unitat orgànica.
     *
     * @param id identificador
     * @return Resposta amb status 204 si l'operació té èxit, o 404 si el recurs amb l'id indicat no existeix.
     */
    @DELETE
    @Path("{id}")
    @Operation(operationId = "deleteUnitat", summary = "Esborra una unitat orgànica")
    @APIResponse(responseCode = "204", description = "Operació realitzada correctament")
    @APIResponse(responseCode = "404", description = "Recurs no trobat")
    public Response delete(@Parameter(description = "L'identificador de la unitat", required = true)
                           @PathParam("id") Long id) {
        unitatOrganicaService.delete(id);
        return Response.noContent().build();
    }
}
