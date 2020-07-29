package es.caib.projectebase.ejb.facade;

import es.caib.projectebase.commons.utils.Constants;
import es.caib.projectebase.ejb.converter.ProcedimentConverter;
import es.caib.projectebase.ejb.interceptor.ExceptionInterceptor;
import es.caib.projectebase.ejb.interceptor.Logged;
import es.caib.projectebase.ejb.repository.ProcedimentRepository;
import es.caib.projectebase.ejb.repository.UnitatOrganicaRepository;
import es.caib.projectebase.persistence.model.Procediment;
import es.caib.projectebase.persistence.model.UnitatOrganica;
import es.caib.projectebase.service.exception.ProcedimentDuplicatExeption;
import es.caib.projectebase.service.exception.RecursNoTrobatException;
import es.caib.projectebase.service.facade.ProcedimentServiceFacade;
import es.caib.projectebase.service.model.Page;
import es.caib.projectebase.service.model.ProcedimentDTO;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.validation.executable.ValidateOnExecution;
import java.util.List;
import java.util.Optional;

/**
 * Implementació dels casos d'ús de manteniment de Procediments.
 * És responsabilitat d'aquesta capa definir el limit de les transaccions i la seguretat.
 *
 * @author areus
 */
@Logged
@Stateless
@Local(ProcedimentServiceFacade.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@RolesAllowed(Constants.PBS_ADMIN)
@ValidateOnExecution
@Interceptors(ExceptionInterceptor.class)
public class ProcedimentServiceFacadeBean implements ProcedimentServiceFacade {

    @Inject
    private UnitatOrganicaRepository unitatRepository;

    @Inject
    private ProcedimentRepository repository;

    @Inject
    private ProcedimentConverter converter;

    @Override
    public Long create(ProcedimentDTO dto, Long idUnitat) throws ProcedimentDuplicatExeption {
        if (repository.findByCodiSia(dto.getCodiSia()).isPresent()) {
            throw new ProcedimentDuplicatExeption(dto.getCodiSia());
        }

        UnitatOrganica unitatReference = unitatRepository.getReference(idUnitat);
        Procediment procediment = converter.toEntity(dto);
        procediment.setUnitatOrganica(unitatReference);
        repository.create(procediment);
        return procediment.getId();
    }

    @Override
    public void update(ProcedimentDTO dto) throws ProcedimentDuplicatExeption {
        Optional<Procediment> opProcediment = repository.findByCodiSia(dto.getCodiSia());
        if (opProcediment.isPresent() && !opProcediment.get().getId().equals(dto.getId())) {
            throw new ProcedimentDuplicatExeption(dto.getCodiSia());
        }

        Procediment procediment = opProcediment.orElse(repository.getReference(dto.getId()));
        converter.updateFromDTO(procediment, dto);
        repository.update(procediment);
    }

    @Override
    public void delete(Long id) {
        Procediment procediment = repository.getReference(id);
        repository.delete(procediment);
    }

    @Override
    public ProcedimentDTO findById(Long id) throws RecursNoTrobatException {
        Procediment procediment = repository.findById(id).orElseThrow(RecursNoTrobatException::new);
        return converter.toDTO(procediment);
    }

    @Override
    public Page<ProcedimentDTO> findByUnitat(int firstResult, int maxResult, Long idUnitat) {

        List<ProcedimentDTO> items = repository.findPagedByUnitat(firstResult, maxResult, idUnitat);
        long total = repository.countByUnitat(idUnitat);

        return new Page<>(items, total);
    }
}
