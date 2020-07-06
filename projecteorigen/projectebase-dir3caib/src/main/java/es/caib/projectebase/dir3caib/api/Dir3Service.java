package es.caib.projectebase.dir3caib.api;

import java.util.List;

public interface Dir3Service {

    List<CodigoValor> comunidadesAutonomas();

    List<CodigoValor> provinciasComunidad(Object idComunidad);

    List<CodigoValor> municipios(Object idProvincia);
}
