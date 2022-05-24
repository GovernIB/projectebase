package es.caib.projectebase.api.interna.distribucio;

import es.caib.projectebase.service.facade.UnitatOrganicaServiceFacade;
import es.caib.projectebase.service.model.AtributUnitat;
import es.caib.projectebase.service.model.EstatPublicacio;
import es.caib.projectebase.service.model.Ordre;
import es.caib.projectebase.service.model.Pagina;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;
import es.caib.sistra2.commons.plugins.dominio.rest.api.v1.RFiltroDominio;
import es.caib.sistra2.commons.plugins.dominio.rest.api.v1.RParametroDominio;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Recurs amb dominis d'exemple per emprar dins DISTRIBUCIO
 */
@Path("distribucio/dominis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DominisResource {

    private static final Logger LOG = LoggerFactory.getLogger(DominisResource.class);

    @Inject
    private UnitatConverter converter;

    @EJB
    private UnitatOrganicaServiceFacade unitatOrganicaService;

    /**
     * Domini d'exemple de DISTRIBUCIO que retorna les unitats administratives actives opcionalment filtrades
     * pel paràmetre "codiDir3"
     *
     * Per provar el domini dins DISTRIBUCIO cal emprar la URL:
     *    http://localhost:8080/projectebase/api/services/distribucio/dominis/unitats
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
                    schema = @Schema(implementation = ValorsDomini.class)))
    public ValorsDomini getDominiUnitats(
            @RequestBody(
                    required = true,
                    description = "Filtre",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RFiltroDominio.class)))
            @NotNull RFiltroDominio filtro) {

        Map<AtributUnitat, Object> filtres = new HashMap<>();
        filtres.put(AtributUnitat.estat, EstatPublicacio.ACTIU);

        for (RParametroDominio parametro : filtro.getFiltro()) {
            if ("codiDir3".equals(parametro.getCodigo())) {
                filtres.put(AtributUnitat.codiDir3, parametro.getValor());
                LOG.info("FITRAR {}", parametro.getValor());
            } else {
                LOG.warn("Paràmetre de filtre '{}' no soportat", parametro.getCodigo());
            }
        }

        Pagina<UnitatOrganicaDTO> filtered = unitatOrganicaService.findFiltered(0, Integer.MAX_VALUE,
                filtres, List.of(Ordre.ascendent(AtributUnitat.codiDir3)));

        ValorsDomini domini = new ValorsDomini();
        domini.setDatos(converter.toMapList(filtered.getItems()));

        return domini;
    }


}
