#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.interna.services;

import ${package}.service.model.Pagina;
import ${package}.service.model.ProcedimentDTO;
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

    @Override
    public List<ProcedimentDTO> getItems() {
        return super.getItems();
    }
}
