package es.caib.projectebase.service;

import es.caib.projectebase.interceptor.Logged;
import es.caib.projectebase.jpa.Procediment;
import es.caib.projectebase.jpa.UnitatOrganica;
import es.caib.projectebase.jpa.dao.ProcedimentDAO;
import es.caib.projectebase.jpa.dao.UnitatOrganicaDAO;

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
