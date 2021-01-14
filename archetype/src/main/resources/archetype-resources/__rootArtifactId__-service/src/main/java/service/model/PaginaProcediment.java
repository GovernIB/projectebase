#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

/**
 * Classe necessària per generar correctament la documentació OpenAPI, atès que els resultats
 * amb genèrics no funcionen del tot bé.
 *
 * És totalment equivalent a un Pagina&lt;ProcedimentDTO&gt; i només s'empra a la documentació del API REST
 *
 * @author areus
 */
@Schema(name = "PaginaProcediment")
public class PaginaProcediment extends Pagina<ProcedimentDTO> {

    public PaginaProcediment(List<ProcedimentDTO> items, long total) {
        super(items, total);
    }
}
