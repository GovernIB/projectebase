package es.caib.projectebase.jpa.dao;

import es.caib.projectebase.jpa.UnitatOrganica;

/**
 * DAO per gestionar {@link UnitatOrganica}.
 *
 * @author areus
 */
public class UnitatOrganicaDAO extends GenericJpaDAO<Long, UnitatOrganica> {

    /**
     * Constructor per defecte.
     */
    public UnitatOrganicaDAO() {
        super(UnitatOrganica.class);
    }

    // MÈTODES ESPECÍFICS PER UNITATS ORGÀNIQUES

    // ....

}
