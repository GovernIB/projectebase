package es.caib.projectebase.ejb.repository;

import es.caib.projectebase.persistence.model.Procediment;

public class ProcedimentRepository extends AbstractCrudRepository<Procediment, Long> {

    protected ProcedimentRepository() {
        super(Procediment.class);
    }
}
