package org.fundaciobit.projectebase.archetype.logic.utils;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;

import org.fundaciobit.projectebase.archetype.utils.Configuracio;
import org.fundaciobit.projectebase.archetype.versio.Versio;

/**
 * 
 * @author anadal
 *
 */
public class LogicUtils {

  protected static Logger log = Logger.getLogger(LogicUtils.class);
  
  
  public static String getVersio() {
    return Versio.VERSIO + (Configuracio.isCAIB()?"-caib" : "");
  }

  
}
