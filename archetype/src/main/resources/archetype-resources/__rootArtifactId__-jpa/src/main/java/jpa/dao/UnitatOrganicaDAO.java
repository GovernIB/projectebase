#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jpa.dao;

import ${package}.jpa.UnitatOrganica;

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
