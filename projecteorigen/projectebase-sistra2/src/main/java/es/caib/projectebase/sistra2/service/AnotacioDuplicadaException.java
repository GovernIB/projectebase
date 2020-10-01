package es.caib.projectebase.sistra2.service;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class AnotacioDuplicadaException extends RuntimeException {

    private final String id;

    public AnotacioDuplicadaException(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
