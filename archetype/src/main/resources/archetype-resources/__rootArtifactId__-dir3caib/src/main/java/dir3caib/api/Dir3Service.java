#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dir3caib.api;

import java.util.List;

/**
 * Subconjunt d'operacions proporcionades per l'API REST de Dir3Caib
 *
 * @author areus
 */
public interface Dir3Service {

    List<CodigoValor> comunidadesAutonomas();

    List<CodigoValor> provinciasComunidad(Object idComunidad);

    List<CodigoValor> municipios(Object idProvincia);

    List<CodigoValor> nivelesAdministracion();

    List<Nodo> busquedaOrganismos(String codigo, String denominacion, Object codNivelAdministracion,
                                  Object codComunidadAutonoma, boolean conOficinas, boolean unidadRaiz,
                                  Object provincia, Object localidad, boolean vigentes);
}
