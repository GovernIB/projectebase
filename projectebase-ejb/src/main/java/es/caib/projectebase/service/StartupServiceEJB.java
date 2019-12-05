package es.caib.projectebase.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * EJB únic que s'executa a la inicialització.
 */
@Singleton
@Startup
public class StartupServiceEJB {

    private static final Logger log = LoggerFactory.getLogger(StartupServiceEJB.class);

    @PostConstruct
    protected void init() {
        // Aquí es podrien llegir les opcions de configuració, i comprovar que tots els paràmetres necessaris hi són,
        // o fixar els valors per defecte pels que no hi siguin, programar timers no persistents, ...
        log.info("Inici del mòdul EJB");
    }

    @PreDestroy
    protected void destroy() {
        log.info("Aturada del mòdul EJB");
    }
}
