#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.interceptor.Logged;
import ${package}.jpa.Procediment;
import ${package}.jpa.UnitatOrganica;
import ${package}.jpa.dao.ProcedimentDAO;
import ${package}.jpa.dao.UnitatOrganicaDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Servei EJB per gestionar {@link Procediment}.
 * Li aplicam l'interceptor {@link Logged}, per tant, totes les cridades es loguejeran.
 *
 * @author areus
 */
@Logged
@Stateless
public class ProcedimentEJB implements ProcedimentService {

    @Inject
    private ProcedimentDAO procedimentDAO;

    @Inject
    private UnitatOrganicaDAO unitatOrganicaDAO;

    @Override
    public Procediment create(Procediment procediment, Long unitatOrganicaId) {
        UnitatOrganica unitatOrganica = unitatOrganicaDAO.getReference(unitatOrganicaId);
        procediment.setUnitatOrganica(unitatOrganica);
        procedimentDAO.create(procediment);
        return procediment;
    }

    @Override
    public Procediment update(Procediment procediment) {
        return procedimentDAO.update(procediment);
    }

    @Override
    public void deleteById(Long id) {
        procedimentDAO.deleteById(id);
    }

    @Override
    public Procediment findById(Long id) {
        return procedimentDAO.findById(id);
    }

    @Override
    public List<Procediment> findAllByUnitatOrganica(Long unitatOrganicaId) {
        return procedimentDAO.findAllByUnitatOrganica(unitatOrganicaId);
    }

    @Override
    public Long countAllByUnitatOrganica(Long unitatOrganicaId) {
        return procedimentDAO.countAllByUnitatOrganica(unitatOrganicaId);
    }

    @Override
    public List<Procediment> findAllByUnitatOrganica(Long unitatOrganicaId, String filter) {
        return procedimentDAO.findAllByUnitatOrganica(unitatOrganicaId, filter);
    }

    @Override
    public Long countAllByUnitatOrganica(Long unitatOrganicaId, String filter) {
        return procedimentDAO.countAllByUnitatOrganica(unitatOrganicaId, filter);
    }
}
