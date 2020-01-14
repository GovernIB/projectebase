#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.persistence.dao;

import ${package}.persistence.UnitatOrganica;

/**
 * DAO per gestionar {@link UnitatOrganica}.
 *
 * @author areus
 */
public class UnitatOrganicaDAO extends AbstractDAO<UnitatOrganica, Long> implements IUnitatOrganicaDAO {

    @Override
    public String getJPATableName() {
        return "UnitatOrganica";
    }

    @Override
    public Class<UnitatOrganica> getJPAClass() {
        return UnitatOrganica.class;
    }

    @Override
    public Long getJPAPrimaryKey(UnitatOrganica entity) {
        if (entity == null) {
            return null;
        } else {
            return entity.getId();
        }
    }


    // MÈTODES ESPECÍFICS PER UNITATS ORGÀNIQUES

    // ....
   
    
}
