package es.caib.projectebase.ws.utils;

import javax.jws.WebMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.caib.projectebase.commons.utils.Version;
import es.caib.projectebase.commons.utils.Constants;

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
    return Version.getVersionInstance().getVersion();
  }


  @WebMethod
  public int getVersionWs() {
    return VersionsWs.VERSIO_WS_1;
  }
}
