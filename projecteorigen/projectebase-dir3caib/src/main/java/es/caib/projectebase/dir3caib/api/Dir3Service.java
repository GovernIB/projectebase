package es.caib.projectebase.dir3caib.api;

import java.util.List;

public interface Dir3Service {

    List<CodigoValor> comunidadesAutonomas();

    List<CodigoValor> provinciasComunidad(Object idComunidad);

    List<CodigoValor> municipios(Object idProvincia);

    List<CodigoValor> nivelesAdministracion();

    List<Nodo> busquedaOrganismos(String codigo, String denominacion, Object codNivelAdministracion,
                                  Object codComunidadAutonoma, boolean conOficinas, boolean unidadRaiz,
                                  Object provincia, Object localidad, boolean vigentes);
}
