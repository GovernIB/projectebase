#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Interceptor per loguejar les cridades als mètodes de la classe interceptada. El feim serializable perquè
 * es pugui aplicar a classes que perquè estiguin a un determinat scope hagin de ser serializables.
 *
 * @author areus
 */
@Logged
@Interceptor
public class LoggerInterceptor implements Serializable {

    private final Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);

    /**
     * Intercepta un mètode de negoci i fa un log a l'inici i al final.
     *
     * @param context Contexte d'invocació.
     * @return El resultat del mètode interceptar.
     * @throws Exception Llança la mateixa excepció que el mètode invocat.
     */
    @AroundInvoke
    public Object logCall(InvocationContext context) throws Exception {
        final String simpleName = context.getTarget().getClass().getSimpleName();
        final String methodName = simpleName + "." + context.getMethod().getName();
        final String callMessage = methodName + Arrays.toString(context.getParameters());
        log.debug(callMessage);

        Object result = context.proceed();
        
        final String returnMessage = methodName + " return(" + result + ")";
        log.debug(returnMessage);
        return result;
    }
}
