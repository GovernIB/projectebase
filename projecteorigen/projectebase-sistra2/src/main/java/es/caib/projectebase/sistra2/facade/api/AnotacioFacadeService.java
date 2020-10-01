package es.caib.projectebase.sistra2.facade.api;

import es.caib.projectebase.sistra2.backoffice.api.AnotacioRegistreId;
import es.caib.projectebase.sistra2.facade.exception.AnotacioIdInvalidException;

import java.util.List;

public interface AnotacioFacadeService {

    void rebreAnotacions(List<AnotacioRegistreId> ids) throws AnotacioIdInvalidException;
}
