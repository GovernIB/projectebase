package es.caib.projectebase.ejb.interceptor;

import es.caib.projectebase.service.exception.RecursNoTrobatException;
import es.caib.projectebase.service.exception.ServiceException;

import javax.ejb.EJBException;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.io.Serializable;

/**
 * Interceptor per capturar excepcions. Permet transformar per un EJB de la capa de serveis les excepcions internes
 * amb excepcions adequades per la capa de serveis.
 * Per exemple, es podrien extreure els errors SQL per llançar excepcions apropiades a la capa de serveis.
 *
 * @author areus
 */
@ExceptionTranslate
@Interceptor
public class ExceptionTranslatorInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Transforma les excepcions.
     *
     * @param context Contexte d'invocació.
     * @return El resultat del mètode interceptar.
     * @throws Exception llança l'excepció processada.
     */
    @AroundInvoke
    public Object aroundInvoke(InvocationContext context) throws Exception {
        try {
            return context.proceed();
        } catch (PersistenceException persistenceException) {
            throw processPersistenceException(persistenceException);
        } catch (EJBException ejbException) {
            Throwable cause = ejbException.getCause();
            if (cause instanceof PersistenceException) {
                throw processPersistenceException((PersistenceException) cause);
            }
            throw ejbException;
        }
    }

    private ServiceException processPersistenceException(PersistenceException exception) {
        if (exception instanceof EntityNotFoundException) {
            return new RecursNoTrobatException();
        }
        // Si es una violació de constraints es podria extreure la SQLException per trobar el codi
        // o emprar la ConstraintViolationException de hibernate.
        return new ServiceException(exception);
    }
}
