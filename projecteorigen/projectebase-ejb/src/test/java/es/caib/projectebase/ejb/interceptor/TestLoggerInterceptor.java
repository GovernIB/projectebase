package es.caib.projectebase.ejb.interceptor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

import javax.interceptor.InvocationContext;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


@RunWith(MockitoJUnitRunner.class)
public class TestLoggerInterceptor {

    private LoggerInterceptor interceptor;

    @Mock
    private Logger log;

    @Mock
    private InvocationContext context;

    @Before
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        //allow log field to be changed
        Field field = LoggerInterceptor.class.getDeclaredField("LOG");
        field.setAccessible(true);

        //remove final modifier
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        //set to mock object
        field.set(null, log);
        interceptor = new LoggerInterceptor();
    }

    @Test
    public void testLogDebugDeshabilitat() throws Exception {

        Mockito.when(log.isDebugEnabled()).thenReturn(false);

        Mockito.when(context.getTarget()).thenReturn(new Object());
        Mockito.when(context.getMethod()).thenReturn(Object.class.getMethod("toString"));
        Mockito.when(context.proceed()).thenReturn(null);

        interceptor.logCall(context);

        Mockito.verify(context).proceed();
        Mockito.verify(log).isDebugEnabled();
        Mockito.verifyNoMoreInteractions(log);
    }

    @Test
    public void testLogDebugHabilitat() throws Exception {

        Mockito.when(log.isDebugEnabled()).thenReturn(true);

        Mockito.when(context.getTarget()).thenReturn(new Object());
        Mockito.when(context.getMethod()).thenReturn(Object.class.getMethod("toString"));
        Mockito.when(context.getParameters()).thenReturn(new Object[0]);
        Mockito.when(context.proceed()).thenReturn("resultat");

        interceptor.logCall(context);

        Mockito.verify(log).isDebugEnabled();
        Mockito.verify(log).debug("{} {}", "Object.toString", "[]");
        Mockito.verify(log).debug(
                Mockito.eq("{} return({}) in {} ms"),
                Mockito.eq("Object.toString"),
                Mockito.eq("resultat"),
                Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(log);
    }

    @Test
    public void testLogDebugHabilitatExceptio() throws Exception {

        Mockito.when(log.isDebugEnabled()).thenReturn(true);

        Mockito.when(context.getTarget()).thenReturn(new Object());
        Mockito.when(context.getMethod()).thenReturn(Object.class.getMethod("toString"));
        Mockito.when(context.getParameters()).thenReturn(new Object[0]);
        Mockito.when(context.proceed()).thenThrow(new Exception());

        try {
            interceptor.logCall(context);
            Assert.fail("No ha d'arribar fins aquí");
        } catch (Exception e) {
            Mockito.verify(log).isDebugEnabled();
            Mockito.verify(log).debug("{} {}", "Object.toString", "[]");
            // només imprimeix el principi, perquè es produeix l'excepció
            Mockito.verifyNoMoreInteractions(log);
        }
    }

}
