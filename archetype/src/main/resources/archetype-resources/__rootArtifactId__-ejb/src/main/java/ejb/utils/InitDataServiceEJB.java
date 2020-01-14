#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb.utils;

import ${package}.commons.i18n.I18NException;
import ${package}.commons.utils.Configuration;
import ${package}.commons.utils.Constants;
import ${package}.ejb.UnitatOrganicaService;
import ${package}.jpa.UnitatOrganica;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * EJB amb mètodes per inicialitzar les dades. Definim que s'executa amb un run-as
 * perquè volem que pugui executar els mètodes de creació de dades que requereixen
 * ser administrador.
 *
 * @author areus
 */
@Stateless
@RunAs(Constants.${prefixuppercase}_ADMIN)
public class InitDataServiceEJB {

    private static final Logger LOG = LoggerFactory.getLogger(InitDataServiceEJB.class);

    @EJB
    private UnitatOrganicaService unitatOrganicaService;

    /**
     * Mètode que inicialitza les dades de prova.
     * TODO emplenat de dades de prova. Millor realitzar-ho a través d'un script SQL
     * L'hem de definir amb un permitAll perquè sinó no es pot cridar d'es del EJB Startup
     */
    @PermitAll
    public void initializeData() {
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
        try {
            unitatOrganicaService.bulkCreate(list);
        } catch (I18NException e) {
            String msg = I18NTranslatorEjb.translate(e, new Locale(Configuration.getDefaultLanguage()));
            throw new RuntimeException(msg);
        }
    }
}
