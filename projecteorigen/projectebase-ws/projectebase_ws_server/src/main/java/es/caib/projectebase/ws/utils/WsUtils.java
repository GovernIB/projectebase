package es.caib.projectebase.ws.utils;

import es.caib.projectebase.commons.i18n.I18NArgument;
import es.caib.projectebase.commons.i18n.I18NArgumentCode;
import es.caib.projectebase.commons.i18n.I18NTranslation;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe amb mètodes d'utilitat de la capa de serveis web.
 *
 * @author anadal
 */
public class WsUtils {

    /**
     * Converteix la classe que representa la traducció d'un missatge a una classe que està
     * preparada per serialitzar-se en XML dins una respota de servei web.
     *
     * @param translation traducció d'un missatge
     * @return representació de la traducció amb una classe serialitzable amb XML.
     */
    public static WsI18NTranslation convertToWsTranslation(I18NTranslation translation) {
        if (translation == null) {
            return null;
        }
        List<WsI18NArgument> args = null;
        I18NArgument[] origArgs = translation.getArgs();
        if (origArgs != null && origArgs.length != 0) {
            args = new ArrayList<>(origArgs.length);
            for (I18NArgument i18nArgument : origArgs) {
                args.add(new WsI18NArgument(i18nArgument.getValue(), i18nArgument instanceof I18NArgumentCode));
            }
        }
        return new WsI18NTranslation(translation.getCode(), args);
    }
}
