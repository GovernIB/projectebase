package es.caib.projectebase.ejb.facade;

import es.caib.projectebase.commons.utils.Constants;
import es.caib.projectebase.ejb.converter.UnitatOrganicaConverter;
import es.caib.projectebase.ejb.interceptor.ExceptionInterceptor;
import es.caib.projectebase.ejb.interceptor.Logged;
import es.caib.projectebase.ejb.repository.UnitatOrganicaRepository;
import es.caib.projectebase.persistence.model.UnitatOrganica;
import es.caib.projectebase.service.exception.RecursNoTrobatException;
import es.caib.projectebase.service.exception.UnitatOrganicaDuplicadaExeption;
import es.caib.projectebase.service.facade.UnitatOrganicaServiceFacade;
import es.caib.projectebase.service.model.Ordre;
import es.caib.projectebase.service.model.Page;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.validation.executable.ValidateOnExecution;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementació dels casos d'ús de manteniment de Unitats Orgàniques.
 * És responsabilitat d'aquesta capa definir el limit de les transaccions i la seguretat.
 *
 * @author areus
 */
@Logged
@Stateless
@Local(UnitatOrganicaServiceFacade.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@RolesAllowed(Constants.PBS_ADMIN)
@ValidateOnExecution
@Interceptors(ExceptionInterceptor.class)
public class UnitatOrganicaServiceFacadeBean implements UnitatOrganicaServiceFacade {

    @Inject
    private UnitatOrganicaRepository repository;

    @Inject
    private UnitatOrganicaConverter converter;

    @Override
    public Long create(UnitatOrganicaDTO dto) throws UnitatOrganicaDuplicadaExeption {
        if (repository.findByCodiDir3(dto.getCodiDir3()).isPresent()) {
            throw new UnitatOrganicaDuplicadaExeption(dto.getCodiDir3());
        }

        UnitatOrganica unitat = converter.toEntity(dto);
        repository.create(unitat);
        return unitat.getId();
    }

    @Override
    public void update(UnitatOrganicaDTO dto) throws UnitatOrganicaDuplicadaExeption {
        Optional<UnitatOrganica> opUnitat = repository.findByCodiDir3(dto.getCodiDir3());
        if (opUnitat.isPresent() && !opUnitat.get().getId().equals(dto.getId())) {
            throw new UnitatOrganicaDuplicadaExeption(dto.getCodiDir3());
        }

        UnitatOrganica unitat = opUnitat.orElse(repository.getReference(dto.getId()));
        converter.updateFromDTO(unitat, dto);
        repository.update(unitat);
    }

    @Override
    public void delete(Long id) {
        UnitatOrganica unitat = repository.getReference(id);
        repository.delete(unitat);
    }

    @Override
    public UnitatOrganicaDTO findById(Long id) throws RecursNoTrobatException {
        UnitatOrganica unitat = repository.findById(id).orElseThrow(RecursNoTrobatException::new);
        return converter.toDTO(unitat);
    }

    @Override
    public Page<UnitatOrganicaDTO> findFiltered(int firstResult, int maxResult, Map<String, Object> filters,
                                                List<Ordre> ordenacio) {

        List<UnitatOrganicaDTO> items = repository.findPagedByFilterAndOrder(firstResult, maxResult, filters, ordenacio);
        long total = repository.countByFilter(filters);

        return new Page<>(items, total);
    }
}
