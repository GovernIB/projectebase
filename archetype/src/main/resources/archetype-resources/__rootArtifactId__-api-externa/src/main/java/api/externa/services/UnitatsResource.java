#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.externa.services;

import ${package}.service.facade.UnitatOrganicaServiceFacade;
import ${package}.service.model.Pagina;
import ${package}.service.model.PaginaUnitatOrganica;
import ${package}.service.model.UnitatOrganicaDTO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

/**
 * Recurs REST per accedir a Unitats Organiques.
 *
 * @author areus
 */
@Path("unitats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UnitatsResource {

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

}
