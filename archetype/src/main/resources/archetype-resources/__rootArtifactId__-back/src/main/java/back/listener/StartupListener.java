#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.listener;

import ${package}.commons.utils.Version;
import ${package}.jpa.UnitatOrganica;
import ${package}.service.UnitatOrganicaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Executat a l'inici del desplegament de l'aplicació web, així com en l'aturada.
 *
 * @author areus
 */
@WebListener
public class StartupListener implements ServletContextListener {

    private static final Logger LOG = LoggerFactory.getLogger(StartupListener.class);

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

        Version version = Version.getVersionInstance();
        LOG.info("${symbol_escape}nWebApp " + version.getProjectName() + ":" + "${symbol_escape}n  + Version: "
                + version.getVersion() + "${symbol_escape}n  + BuildTime: " + version.getBuildTime()
                + "${symbol_escape}n  + Revision: " + version.getScmRevision());

        // TODO emplenat de dades de prova. Millor realitzar-ho a través d'un script SQL
        LOG.info("Iniciant la creació d'unitats organiques de prova");
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
        unitatOrganicaService.bulkCreate(list);
    }

    /**
     * Executat quan es destrueix el contexte web.
     *
     * @param sce
     *            Informació de l'esdeveniment de context.
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("Aturant aplicació ${symbol_dollar}{project_name}");
    }
}
