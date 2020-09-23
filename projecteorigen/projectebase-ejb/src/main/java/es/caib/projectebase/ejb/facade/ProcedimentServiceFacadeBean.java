package es.caib.projectebase.ejb.facade;

import es.caib.projectebase.commons.utils.Constants;
import es.caib.projectebase.ejb.converter.ProcedimentConverter;
import es.caib.projectebase.ejb.interceptor.Logged;
import es.caib.projectebase.ejb.repository.ProcedimentRepository;
import es.caib.projectebase.ejb.repository.UnitatOrganicaRepository;
import es.caib.projectebase.persistence.model.Procediment;
import es.caib.projectebase.service.exception.ProcedimentDuplicatException;
import es.caib.projectebase.service.facade.ProcedimentServiceFacade;
import es.caib.projectebase.service.model.Pagina;
import es.caib.projectebase.service.model.ProcedimentDTO;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Implementació dels casos d'ús de manteniment de Procediments.
 * És responsabilitat d'aquesta capa definir el limit de les transaccions i la seguretat.
 *
 * @author areus
 */
@Logged
@Stateless @Local(ProcedimentServiceFacade.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@RolesAllowed(Constants.PBS_ADMIN)
public class ProcedimentServiceFacadeBean implements ProcedimentServiceFacade {

    @Inject
    private UnitatOrganicaRepository unitatRepository;

    @Inject
    private ProcedimentRepository repository;

    @Inject
    private ProcedimentConverter converter;

    @Override
    public Long create(ProcedimentDTO dto, Long idUnitat) throws ProcedimentDuplicatException {
        // Comprovam que el codiSia no existeix ja
        if (repository.findByCodiSia(dto.getCodiSia()).isPresent()) {
            throw new ProcedimentDuplicatException(dto.getCodiSia());
        }

        Procediment procediment = converter.toEntity(dto);
        procediment.setUnitatOrganica(unitatRepository.getReference(idUnitat));
        repository.create(procediment);
        return procediment.getId();
    }

    @Override
    public void update(ProcedimentDTO dto) {
        Procediment procediment = repository.getReference(dto.getId());
        converter.updateFromDTO(procediment, dto);
    }

    @Override
    public void delete(Long id) {
        Procediment procediment = repository.getReference(id);
        repository.delete(procediment);
    }

    @Override
    public Optional<ProcedimentDTO> findById(Long id) {
        Procediment procediment = repository.findById(id);
        ProcedimentDTO procedimentDTO = converter.toDTO(procediment);
        return Optional.ofNullable(procedimentDTO);
    }

    @Override
    public Pagina<ProcedimentDTO> findByUnitat(int firstResult, int maxResult, Long idUnitat) {

        List<ProcedimentDTO> items = repository.findPagedByUnitat(firstResult, maxResult, idUnitat);
        long total = repository.countByUnitat(idUnitat);

        return new Pagina<>(items, total);
    }
}
