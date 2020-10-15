package es.caib.projectebase.ejb.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;

@RunWith(MockitoJUnitRunner.class)
public class TestAbstractCrudRepository {

    private CrudRepository<Object, Long> repository;

    @Mock
    private EntityManager entityManager;

    @Before
    public void setup() {
        AbstractCrudRepository<Object, Long> repositoryBean = new AbstractCrudRepository<>(Object.class) {};

        repositoryBean.entityManager = entityManager;
        repository = repositoryBean;
    }

    @Test
    public void testCreate() {
        Object object = new Object();
        repository.create(object);

        Mockito.verify(entityManager).persist(object);
        Mockito.verify(entityManager).flush();
        Mockito.verifyNoMoreInteractions(entityManager);
    }

    @Test
    public void testUpdate() {
        Object object = new Object();
        repository.update(object);

        Mockito.verify(entityManager).merge(object);
        Mockito.verify(entityManager).flush();
        Mockito.verifyNoMoreInteractions(entityManager);
    }

    @Test
    public void testDelete() {
        Object object = new Object();

        repository.delete(object);

        Mockito.verify(entityManager).remove(object);
        Mockito.verify(entityManager).flush();
        Mockito.verifyNoMoreInteractions(entityManager);
    }

    @Test
    public void testFindById() {
        Object object = new Object();
        Mockito.when(entityManager.find(Object.class, 1L)).thenReturn(object);

        Object result = repository.findById(1L);
        Assert.assertEquals(object, result);

        Mockito.verify(entityManager).find(Object.class, 1L);
        Mockito.verifyNoMoreInteractions(entityManager);
    }

    @Test
    public void testFindByIdNotFound() {

        Mockito.when(entityManager.find(Object.class, 1L)).thenReturn(null);

        Object result = repository.findById(1L);
        Assert.assertNull(result);

        Mockito.verify(entityManager).find(Object.class, 1L);
        Mockito.verifyNoMoreInteractions(entityManager);
    }

    @Test
    public void testGetReference() {
        Object object = new Object();
        Mockito.when(entityManager.getReference(Object.class, 1L)).thenReturn(object);

        Object result = repository.getReference(1L);
        Assert.assertEquals(object, result);

        Mockito.verify(entityManager).getReference(Object.class, 1L);
        Mockito.verifyNoMoreInteractions(entityManager);
    }

}
