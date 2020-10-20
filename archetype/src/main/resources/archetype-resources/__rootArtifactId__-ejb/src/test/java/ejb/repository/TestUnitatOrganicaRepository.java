#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb.repository;

import ${package}.persistence.model.UnitatOrganica;
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
public class TestUnitatOrganicaRepository {

    private UnitatOrganicaRepository repository;

    @Mock
    private EntityManager entityManager;

    @Before
    public void setup() {
        UnitatOrganicaRepositoryBean repositoryBean = new UnitatOrganicaRepositoryBean();
        repositoryBean.entityManager = entityManager;
        repository = repositoryBean;
    }

    @Test
    public void testfindByCodiDir3Present() {
        UnitatOrganica unitat = new UnitatOrganica();
        unitat.setCodiDir3("A012345678");

        @SuppressWarnings("unchecked")
        TypedQuery<UnitatOrganica> mockedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(mockedQuery.getResultList()).thenReturn(List.of(unitat));
        Mockito.when(entityManager.createNamedQuery(UnitatOrganica.FIND_BY_CODIDIR3, UnitatOrganica.class))
                .thenReturn(mockedQuery);

        Optional<UnitatOrganica> result = repository.findByCodiDir3("A012345678");

        Mockito.verify(mockedQuery).setParameter("codiDir3", "A012345678");
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(unitat, result.get());
    }

    @Test
    public void testfindByCodiDir3NotPresent() {

        @SuppressWarnings("unchecked")
        TypedQuery<UnitatOrganica> mockedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(mockedQuery.getResultList()).thenReturn(Collections.emptyList());
        Mockito.when(entityManager.createNamedQuery(UnitatOrganica.FIND_BY_CODIDIR3, UnitatOrganica.class))
                .thenReturn(mockedQuery);

        Optional<UnitatOrganica> result = repository.findByCodiDir3("A012345678");

        Mockito.verify(mockedQuery).setParameter("codiDir3", "A012345678");
        Assert.assertTrue(result.isEmpty());
    }

}
