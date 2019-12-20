#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.interceptor.Logged;
import ${package}.jpa.UnitatOrganica;
import ${package}.jpa.dao.UnitatOrganicaDAO;

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
    private UnitatOrganicaDAO dao;

    @Override
    public void bulkCreate(@NotNull List<UnitatOrganica> unitats) {
        unitats.forEach(dao::create);
    }

    @Override
    public UnitatOrganica create(UnitatOrganica unitatOrganica) {
        return dao.create(unitatOrganica);
    }

    @Override
    public UnitatOrganica update(UnitatOrganica unitatOrganica) {
        return dao.update(unitatOrganica);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public UnitatOrganica findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<UnitatOrganica> findAll() {
        return dao.findAll();
    }

    @Override
    public List<UnitatOrganica> findAll(@PositiveOrZero int first, @Positive int pageSize) {
        return dao.findAll(first, pageSize);
    }

    @Override
    public long countAll() {
        return dao.countAll();
    }

    @Override
    public List<UnitatOrganica> findFiltered(String filter, @PositiveOrZero int first, @Positive int pageSize) {
        return dao.findFiltered(filter, first, pageSize);
    }

    @Override
    public long countFiltered(String filter) {
        return dao.countFiltered(filter);
    }
}
