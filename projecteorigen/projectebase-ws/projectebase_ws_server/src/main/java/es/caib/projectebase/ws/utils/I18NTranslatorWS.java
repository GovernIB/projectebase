package es.caib.projectebase.ws.utils;

import es.caib.projectebase.commons.i18n.I18NTranslator;
import es.caib.projectebase.ejb.utils.I18NTranslatorEjb;

/**
 * Clase d'utilitat per traduir missatges I18N dins el mòdul WS.
 *
 * @author anadal
 */
public class I18NTranslatorWS extends I18NTranslatorEjb {

    public static final I18NTranslator translator = new I18NTranslator(
            new String[] { "ValidationMessages", "persistence.LabelsPersistence", "ejb.LabelsEJB"});

}
