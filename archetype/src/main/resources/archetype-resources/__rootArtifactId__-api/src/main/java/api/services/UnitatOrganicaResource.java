#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.services;

import ${package}.commons.i18n.I18NException;
import ${package}.ejb.UnitatOrganicaService;
import ${package}.persistence.UnitatOrganica;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Recurs REST per accedir a Unitats Organiques.
 *
 * @author areus
 */
@Path("unitats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UnitatOrganicaResource {

    @EJB
    private UnitatOrganicaService unitatOrganicaService;

    /**
     * Retorna totes les unitats orgàniques.
     *
     * @return Un codi 200 amb totes les unitats orgàniques.
     */
    @GET
    @Operation(summary = "Retorna una llista de totes les unitats orgàniques")
    @APIResponse(
            responseCode = "200",
            description = "Llista d'unitats orgàniques",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(type = SchemaType.ARRAY, implementation = UnitatOrganica.class)))
    public Response getAll() throws I18NException {
        List<UnitatOrganica> all = unitatOrganicaService.findAll();
        return Response.ok().entity(all).build();
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
    @Operation(summary = "Obté una unitat orgànica")
    @APIResponse(responseCode = "200",
            description = "Unitat orgànica",
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

    /**
     * Crea una nova unitat orgànica.
     *
     * @param unitatOrganica la nova unitat orgànica a crear.
     * @return Un codi 201 amb la localització de la unitat orgància creada.
     */
    @POST
    @Operation(summary = "Crea una nova unitat orgànica")
    @APIResponse(responseCode = "201", description = "L'enllaç a la unitat orgànica creada")
    public Response create(
            @RequestBody(
                    description = "Unitat orgànica",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnitatOrganica.class)))
            @Valid UnitatOrganica unitatOrganica) throws I18NException {
        unitatOrganicaService.create(unitatOrganica);
        return Response.created(URI.create("unitats/" + unitatOrganica.getId())).build();
    }

    /**
     * Actualitza una unitat orgànica. Carrega la unitat orgànica indicada per l'identificador i l'actualitza
     * amb els camps rebuts.
     *
     * @param unitatOrganica dades de la unitat orgànica a actualitzar.
     * @param id Identificador de la unitat orgància a actualitzar.
     * @return Un codi 200 si la modificació va bé, 404 si la unidad indicada per l'id no existeix.
     */
    @PUT
    @Path("{id}")
    @Operation(summary = "Actualitza una unitat orgànica")
    @APIResponse(responseCode = "200", description = "La modificació s'ha realitzat correctament")
    @APIResponse(responseCode = "404", description = "La unitat orgànica que es vol modificar no existeix.")
    public Response update(
            @RequestBody(
                    description = "Unitat orgànica",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnitatOrganica.class)))
            @Valid UnitatOrganica unitatOrganica,
            @Parameter(description = "L'identificador de la unitat", required = true)
            @PathParam("id") Long id) throws I18NException {

        UnitatOrganica unitatActual = unitatOrganicaService.findById(id);
        if (unitatActual == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            unitatActual.setNom(unitatOrganica.getNom());
            unitatActual.setCodiDir3(unitatOrganica.getCodiDir3());
            unitatActual.setEstat(unitatOrganica.getEstat());
            unitatActual.setDataCreacio(unitatOrganica.getDataCreacio());
            unitatOrganicaService.update(unitatActual);
            return Response.ok().build();
        }
    }

    /**
     * Esborra una unitat orgànica.
     *
     * @param id identificador
     * @return Resposta amb status 200 que indica que l'operaicó ha tengut èxit.
     */
    @DELETE
    @Path("{id}")
    @Operation(summary = "Esborra una unitat orgànica")
    @APIResponse(responseCode = "200", description = "La unitat s'ha esborrat correctament")
    @APIResponse(responseCode = "404", description = "Unitat orgànica no trobada")
    public Response delete(@Parameter(description = "L'identificador de la unitat", required = true)
                        @PathParam("id") Long id) throws I18NException {
        unitatOrganicaService.deleteById(id);
        return Response.ok().build();
    }
}
