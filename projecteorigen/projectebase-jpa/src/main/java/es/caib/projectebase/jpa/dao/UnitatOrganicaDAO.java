package es.caib.projectebase.jpa.dao;

import es.caib.projectebase.jpa.UnitatOrganica;

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