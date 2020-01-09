package es.caib.projectebase.back.listener;

import es.caib.projectebase.commons.i18n.I18NException;
import es.caib.projectebase.commons.utils.Configuration;
import es.caib.projectebase.commons.utils.Version;
import es.caib.projectebase.ejb.UnitatOrganicaService;
import es.caib.projectebase.ejb.utils.I18NTranslatorEjb;
import es.caib.projectebase.jpa.UnitatOrganica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Executat a l'inici del desplegament de l'aplicació web, així com en l'aturada.
 *
 * @author areus
 */
@WebListener
public class StartupListener implements ServletContextListener {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private Version version;

    @EJB
    private UnitatOrganicaService unitatOrganicaService;

    /**
     * Executat quan s'inicialitza el contexte web. Treu un missatge amb la versió als logs.
     *
     * @param sce
     *            Informació de l'esdeveniment de context.
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("\nWebApp " + version.getProjectName() + ":" + "\n  + Version: "
                + version.getVersion() + "\n  + BuildTime: " + version.getBuildTime()
                + "\n  + Revision: " + version.getScmRevision());

        // TODO emplenat de dades de prova. Millor realitzar-ho a través d'un script SQL
        log.info("Iniciant la creació d'unitats organiques de prova");
        DecimalFormat format = new DecimalFormat("00000000");
        List<UnitatOrganica> list = new ArrayList<>(30);
        for (int i = 0; i < 30; i++) {
            UnitatOrganica uo = new UnitatOrganica();
            String codiDir3 = "A" + format.format(i);
            uo.setCodiDir3(codiDir3);
            uo.setNom("Unitat " + i);
            uo.setDataCreacio(LocalDate.now());
            list.add(uo);
        }
        try {
            unitatOrganicaService.bulkCreate(list);
        } catch (I18NException e) {
            String msg = I18NTranslatorEjb.tradueix(e, new Locale(Configuration.getDefaultLanguage()));
            throw new RuntimeException(msg);
        }
    }

    /**
     * Executat quan es destrueix el contexte web.
     *
     * @param sce
     *            Informació de l'esdeveniment de context.
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Aturant aplicació ${project_name}");
    }
}
