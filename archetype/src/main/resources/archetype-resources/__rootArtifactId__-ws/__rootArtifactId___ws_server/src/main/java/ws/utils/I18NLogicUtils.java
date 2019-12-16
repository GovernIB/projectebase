#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.utils;

import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;

/**
 * 
 * @author anadal
 * 
 */
public class I18NLogicUtils extends I18NCommonUtils {

  static {
    BUNDLES = new String[] { "${artifactId}_genapp", "genapp" };
  }

}
