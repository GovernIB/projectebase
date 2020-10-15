package es.caib.projectebase.ejb.repository;

import es.caib.projectebase.persistence.model.Procediment;
import es.caib.projectebase.service.model.ProcedimentDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TestProcedimentRepository {

    private ProcedimentRepository repository;

    @Mock
    private EntityManager entityManager;

    @Before
    public void setup() {
        ProcedimentRepositoryBean repositoryBean = new ProcedimentRepositoryBean();
        repositoryBean.entityManager = entityManager;
        repository = repositoryBean;
    }

    @Test
    public void testFindByCodisiaPresent() {
        Procediment procediment = new Procediment();
        procediment.setCodiSia("0123456");

        @SuppressWarnings("unchecked")
        TypedQuery<Procediment> mockedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(mockedQuery.getResultList()).thenReturn(List.of(procediment));
        Mockito.when(entityManager.createNamedQuery(Procediment.FIND_BY_CODISIA, Procediment.class))
                .thenReturn(mockedQuery);

        Optional<Procediment> result = repository.findByCodiSia("0123456");

        Mockito.verify(mockedQuery).setParameter("codiSia", "0123456");
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(procediment, result.get());
    }

    @Test
    public void testFindByCodisiaNotPresent() {

        @SuppressWarnings("unchecked")
        TypedQuery<Procediment> mockedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(mockedQuery.getResultList()).thenReturn(Collections.emptyList());
        Mockito.when(entityManager.createNamedQuery(Procediment.FIND_BY_CODISIA, Procediment.class))
                .thenReturn(mockedQuery);

        Optional<Procediment> result = repository.findByCodiSia("0123456");

        Mockito.verify(mockedQuery).setParameter("codiSia", "0123456");
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testCountByUnitat() {

        @SuppressWarnings("unchecked")
        TypedQuery<Long> mockedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(mockedQuery.getSingleResult()).thenReturn(123L);
        Mockito.when(entityManager.createNamedQuery(Procediment.COUNT_BY_IDUNITAT, Long.class)).thenReturn(mockedQuery);

        long result = repository.countByUnitat(1L);

        Mockito.verify(mockedQuery).setParameter("idUnitat", 1L);
        Assert.assertEquals(123L, result);
    }


    @Test
    public void testFindPagedByUnitatEmpty() {

        @SuppressWarnings("unchecked")
        TypedQuery<ProcedimentDTO> mockedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(mockedQuery.getResultList()).thenReturn(Collections.emptyList());
        Mockito.when(entityManager.createNamedQuery(Procediment.FIND_DTO_BY_IDUNITAT, ProcedimentDTO.class))
                .thenReturn(mockedQuery);

        List<ProcedimentDTO> result = repository.findPagedByUnitat(3, 13, 1L);

        Mockito.verify(mockedQuery).setFirstResult(3);
        Mockito.verify(mockedQuery).setMaxResults(13);
        Mockito.verify(mockedQuery).setParameter("idUnitat", 1L);

        Assert.assertTrue(result.isEmpty());
    }
}
