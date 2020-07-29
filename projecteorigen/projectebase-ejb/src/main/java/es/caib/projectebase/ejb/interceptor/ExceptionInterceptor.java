package es.caib.projectebase.ejb.interceptor;

import es.caib.projectebase.service.exception.RecursNoTrobatException;
import es.caib.projectebase.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJBTransactionRolledbackException;
import javax.interceptor.AroundInvoke;
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
public class ExceptionInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionInterceptor.class);

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
        } catch (EJBTransactionRolledbackException ejbException) {
            Throwable cause = ejbException.getCause();
            LOG.error("Error a la capa EJB, classe excepció origen de l'error: {}", cause.getClass());
            if (cause instanceof EntityNotFoundException) {
                throw new RecursNoTrobatException();
            }
            if (cause instanceof PersistenceException) {
                // Si es una violació de constraints es podria extreure la SQLException per trobar el codi
                // o emprar la ConstraintViolationException de hibernate.
                throw new ServiceException(cause);
            }
            throw ejbException;
        }
    }



}
