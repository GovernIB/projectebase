#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jpa.dao;


import ${package}.jpa.Procediment;

/**
 * Manager per JPA per gestionar {@link Procediment}.
 *
 * @author areus
 * @author anadal
 */
public class ProcedimentManager extends AbstractJPAManager<Procediment, Long> implements IProcedimentManager {

    @Override
    public String getJPATableName() {
        return "Procediment";
    }

    @Override
    public Class<Procediment> getJPAClass() {
        return Procediment.class;
    }

    @Override
    public Long getJPAPrimaryKey(Procediment entity) {
        if (entity == null) {
            return null;
        } else {
            return entity.getId();
        }
    } 

}
