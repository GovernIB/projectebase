package es.caib.proyectobase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;

@Named
@ApplicationScoped
public class LoggerProducer {

   protected LoggerProducer() {
   }

   @Produces
   public Logger produceLogger(InjectionPoint injectionPoint) {
      return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
   }

}