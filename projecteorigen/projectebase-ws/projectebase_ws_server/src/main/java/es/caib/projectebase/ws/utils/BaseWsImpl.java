package es.caib.projectebase.ws.utils;

import javax.inject.Inject;
import javax.jws.WebMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.caib.projectebase.commons.utils.Version;
import es.caib.projectebase.commons.utils.Constants;

/**
 * 
 * @author anadal
 */
public class BaseWsImpl {

  protected final Logger log = LoggerFactory.getLogger(getClass());

  @Inject
  private Version version;
  
  @WebMethod
  public String getVersion() {
    return version.getVersion();
  }
}
