package es.caib.projectebase.ejb.repository;

import es.caib.projectebase.persistence.model.UnitatOrganica;

public class UnitatOrganicaRepository extends AbstractCrudRepository<UnitatOrganica, Long> {

    protected UnitatOrganicaRepository() {
        super(UnitatOrganica.class);
    }
}
