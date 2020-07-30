#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb.facade;

import ${package}.commons.utils.Constants;
import ${package}.ejb.converter.ProcedimentConverter;
import ${package}.ejb.interceptor.ExceptionInterceptor;
import ${package}.ejb.interceptor.Logged;
import ${package}.ejb.repository.ProcedimentRepository;
import ${package}.ejb.repository.UnitatOrganicaRepository;
import ${package}.persistence.model.Procediment;
import ${package}.persistence.model.UnitatOrganica;
import ${package}.service.exception.ProcedimentDuplicatExeption;
import ${package}.service.exception.RecursNoTrobatException;
import ${package}.service.facade.ProcedimentServiceFacade;
import ${package}.service.model.Page;
import ${package}.service.model.ProcedimentDTO;

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
@RolesAllowed(Constants.${prefixuppercase}_ADMIN)
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
