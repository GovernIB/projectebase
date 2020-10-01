package es.caib.projectebase.sistra2.facade.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AnotacioIdInvalidException extends RuntimeException {

    public AnotacioIdInvalidException(String id, String clauAccess) {
        super("Anotació invalida: ID: '" + id + "', CLAU: '" + clauAccess + "'");
    }
}
