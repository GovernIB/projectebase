package es.caib.projectebase.service;

import es.caib.projectebase.interceptor.Logged;
import es.caib.projectebase.jpa.UnitatOrganica;
import es.caib.projectebase.jpa.dao.UnitatOrganicaDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * Servei EJB per gestionar {@link UnitatOrganica}.
 * Li aplicam l'interceptor {@link Logged}, per tant, totes les cridades es loguejeran.
 *
 * @author areus
 */
@Logged
@Stateless
public class UnitatOrganicaEJB implements UnitatOrganicaService {

    @Inject
    private UnitatOrganicaDAO unitatOrganicaDAO;

    @Override
    public void bulkCreate(@NotNull List<UnitatOrganica> unitats) {
        unitats.forEach(unitatOrganicaDAO::create);
    }

    @Override
    public UnitatOrganica create(UnitatOrganica unitatOrganica) {
        return unitatOrganicaDAO.create(unitatOrganica);
    }

    @Override
    public UnitatOrganica update(UnitatOrganica unitatOrganica) {
        return unitatOrganicaDAO.update(unitatOrganica);
    }

    @Override
    public void deleteById(Long id) {
        unitatOrganicaDAO.deleteById(id);
    }

    @Override
    public UnitatOrganica findById(Long id) {
        return unitatOrganicaDAO.findById(id);
    }

    @Override
    public List<UnitatOrganica> findAll() {
        return unitatOrganicaDAO.findAll();
    }

    @Override
    public List<UnitatOrganica> findAll(@PositiveOrZero int first, @Positive int pageSize) {
        return unitatOrganicaDAO.findAll(first, pageSize);
    }

    @Override
    public long countAll() {
        return unitatOrganicaDAO.countAll();
    }

    @Override
    public List<UnitatOrganica> findFiltered(String filter, @PositiveOrZero int first, @Positive int pageSize) {
        return unitatOrganicaDAO.findFiltered(filter, first, pageSize);
    }

    @Override
    public long countFiltered(String filter) {
        return unitatOrganicaDAO.countFiltered(filter);
    }
}
