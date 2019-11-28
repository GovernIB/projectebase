package es.caib.projectebase.back.listener;

import es.caib.projectebase.Version;
import es.caib.projectebase.jpa.UnitatOrganica;
import es.caib.projectebase.service.UnitatOrganicaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Executat a l'inici del desplegament de l'aplicació web, així com en l'aturada.
 *
 * @author areus
 */
@WebListener
public class StartupListener implements ServletContextListener {

    private Logger log = LoggerFactory.getLogger(StartupListener.class);

    @Inject
    private Version version;

    @EJB
    private UnitatOrganicaService unitatOrganicaService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Aplicació PROJECTEBASE versión: " + version.getVersion() +
                " generada en " + version.getBuildTime());

        log.info("Iniciant la creació d'unitats organiques");
        DecimalFormat format = new DecimalFormat("00000000");
        List<UnitatOrganica> list = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            UnitatOrganica uo = new UnitatOrganica();
            String codiDir3 = "A" + format.format(i);
            uo.setCodiDir3(codiDir3);
            uo.setNom("Unitat " + i);
            uo.setDataCreacio(new Date());
            list.add(uo);
        }
        unitatOrganicaService.bulkCreate(list);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Aturant aplicació PROJECTEBASE");
    }
}
