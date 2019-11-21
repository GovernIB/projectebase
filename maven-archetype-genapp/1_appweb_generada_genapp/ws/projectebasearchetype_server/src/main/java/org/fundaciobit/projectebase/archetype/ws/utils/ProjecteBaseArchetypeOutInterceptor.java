package org.fundaciobit.projectebase.archetype.ws.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;


/**
 * 
 * @author anadal
 */
public class ProjecteBaseArchetypeOutInterceptor extends AbstractPhaseInterceptor<Message> {

  protected final Log log = LogFactory.getLog(getClass());

  public ProjecteBaseArchetypeOutInterceptor() {
    // Veure https://cxf.apache.org/docs/interceptors.html
    super(Phase.SEND);
  }

  @SuppressWarnings("unchecked")
  public void handleMessage(Message message) throws Fault {
    //UsuariAplicacioCache.remove();
  }


}