#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.utils;

import javax.jws.WebMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.Version;
import ${package}.commons.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
public class BaseWsImpl implements Constants {

  protected final Logger log = LoggerFactory.getLogger(getClass());
  
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // --------------------------| UTILITATS |----------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  
  @WebMethod
  public String getVersion() {
    return new Version().getVersion();
  }


  @WebMethod
  public int getVersionWs() {
    return VersionsWs.VERSIO_WS_1;
  }
}
