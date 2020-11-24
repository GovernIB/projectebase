#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.sistra2;

import ${package}.service.facade.UnitatOrganicaServiceFacade;
import ${package}.service.model.EstatPublicacio;
import ${package}.service.model.Pagina;
import ${package}.service.model.UnitatOrganicaDTO;
import es.caib.sistra2.commons.plugins.dominio.rest.api.v1.RFiltroDominio;
import es.caib.sistra2.commons.plugins.dominio.rest.api.v1.RParametroDominio;
import es.caib.sistra2.commons.plugins.dominio.rest.api.v1.RValoresDominio;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Recurs amb dominis d'exemple per emprar dins SISTRA2
 */
@Path("sistra2/dominis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DominisResource {

    private static final Logger LOG = LoggerFactory.getLogger(DominisResource.class);

    @Inject
    private UnitatConverter converter;

    @EJB
    private UnitatOrganicaServiceFacade unitatOrganicaService;

    /**
     * Domini d'exemple de SISTRA2 que retorna les unitats administratives actives opcionalment filtrades
     * pel paràmetre "codiDir3"
     *
     * Per provar el domini dins SISTRA2 cal emprar la URL:
     *    http://localhost:8080/${parentArtifactId}/api/services/sistra2/dominis/unitats
     *
     * @param filtro filtre a aplicar. Només soporta el paràmetre "codiDir3"
     * @return domini amb les unitats administratives actives que complexien el filtre.
     */
    @POST
    @Path("unitats")
    @Operation(operationId = "getDominiUnitats", summary = "Retorna el domini d'unitats")
    @APIResponse(
            responseCode = "200",
            description = "Domini d'unitats",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RValoresDominio.class)))
    public RValoresDominio getDominiUnitats(
            @RequestBody(
                    required = true,
                    description = "Filtre",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RFiltroDominio.class)))
            @NotNull RFiltroDominio filtro) {

        Map<String, Object> filtres = new HashMap<>();
        filtres.put("estat", EstatPublicacio.ACTIU);

        for (RParametroDominio parametro : filtro.getFiltro()) {
            if ("codiDir3".equals(parametro.getCodigo())) {
                filtres.put("codiDir3", parametro.getValor());
            } else {
                LOG.warn("Paràmetre de filtre '{}' no soportat", parametro.getCodigo());
            }
        }

        Pagina<UnitatOrganicaDTO> filtered = unitatOrganicaService.findFiltered(0, Integer.MAX_VALUE,
                filtres, Collections.emptyList());

        RValoresDominio dominio = new RValoresDominio();
        dominio.getDatos().addAll(converter.toMapList(filtered.getItems()));

        return dominio;
    }


}
