#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.api.v1.utils;

import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;

/**
 * 
 * @author anadal
 * 
 */
public class I18NUtils extends I18NCommonUtils {
  
  static {
    BUNDLES = new String[] { "logicmissatges", "${artifactId}_genapp", "genapp" };
  }

}
