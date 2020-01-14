#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.utils;

import ${package}.commons.i18n.I18NTranslator;
import ${package}.ejb.utils.I18NTranslatorEjb;

/**
 *
 * @author anadal
 * 
 */
public class I18NTranslatorWS extends I18NTranslatorEjb {

    public static final I18NTranslator translator = new I18NTranslator(
            new String[] { "ValidationMessages", "persistence.LabelsPersistence", "ejb.LabelsEJB", "${rootArtifactId}_genapp", "genapp" });

}
