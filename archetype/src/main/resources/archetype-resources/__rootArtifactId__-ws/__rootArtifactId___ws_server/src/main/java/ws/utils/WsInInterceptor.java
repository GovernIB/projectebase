#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.utils;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.security.SecurityContext;
import org.apache.cxf.service.model.BindingOperationInfo;

import ${package}.commons.i18n.I18NException;
import ${package}.commons.i18n.I18NValidationException;
import ${package}.commons.utils.Configuration;
import ${package}.commons.utils.Constants;
import org.apache.cxf.service.Service;
import org.apache.cxf.service.invoker.MethodDispatcher;

/**
 * 
 * @author anadal Veure https://cxf.apache.org/docs/interceptors.html
 */

public class WsInInterceptor extends AbstractPhaseInterceptor<Message> {

  // javax.xml.namespace.
  
  protected static final QName QNAME = new javax.xml.namespace.QName("-1");

  protected final Logger log = LoggerFactory.getLogger(getClass());

  public WsInInterceptor() {
    // Veure https://cxf.apache.org/docs/interceptors.html
    super(Phase.PRE_INVOKE);
  }

  public void handleMessage(Message message) throws Fault {

    boolean logEnable = true; // log.isDebugEnabled();

    if (logEnable) {
      log.info(" ------------------ WsInInterceptor  --------------");

      try {

        Method method = getTargetMethod(message);

        log.info("  + Method NAME = " + method.getName());
        log.info("  + Method CLASS = " + method.getDeclaringClass());

        HttpServletRequest hsr = (HttpServletRequest) message.get("HTTP.REQUEST");
        log.info(" USR_1:  " + hsr.getRemoteUser());

        log.info(" ROLE: ${prefixuppercase}_ADMIN  " + hsr.isUserInRole(Constants.${prefixuppercase}_ADMIN));
        log.info(" ROLE: ${prefixuppercase}_USER  " + hsr.isUserInRole(Constants.${prefixuppercase}_USER));

      } catch (Exception e) {
        log.error(e.getMessage());
      }

      log.info("WsInInterceptor::handleMessage() =>  Thread = "
          + Thread.currentThread().getId());
    }

    SecurityContext context = message.get(SecurityContext.class);
    if (context == null || context.getUserPrincipal() == null) {
      log.error("S'ha cridat a l'API sense autenticar la petició.");
      return;
    }

    String userapp = context.getUserPrincipal().getName();
    // DEBUG
    if (logEnable) {
      log.info("WsInInterceptor::handleMessage() => user " + userapp);
      log.info("WsInInterceptor::handleMessage() => ${prefixuppercase}_USER "
          + context.isUserInRole(Constants.${prefixuppercase}_USER));
      log.info("WsInInterceptor::handleMessage() => ${prefixuppercase}_ADMIN "
          + context.isUserInRole(Constants.${prefixuppercase}_ADMIN));
    }

    // TODO GENAPP Get username from DATA-BASE
    String usuariAplicacio = null;

    // Si estam a CAIB llavors s'ha autenticat i autoritzat contra seycon,
    // però a més hauria de tenir usuari dins la webapp
    if (Configuration.isCAIB()) {

      if (usuariAplicacio == null) {
        // TODO Traduccio
        // LogicI18NUtils.translate(loc, code, args)
        final String msg = "L´usuari aplicació " + userapp
            + " està autenticat en el domini de seguretat,"
            + " però no esta donat d'alta dins la WebApp.";

        log.error("WsInInterceptor::handleMessage(CAIB) ", new Exception(msg));
        SoapFault sf;

        sf = new SoapFault(msg, QNAME);
        throw sf;
      }

    } else {
      if (usuariAplicacio == null) {
        log.error("WsInInterceptor::handleMessage(DEFAULT) Usuari APP = null");
        return;
      }
    }
  }

  private Method getTargetMethod(Message m) {
    BindingOperationInfo bop = m.getExchange().get(BindingOperationInfo.class);
    MethodDispatcher md = (MethodDispatcher) m.getExchange().get(Service.class)
        .get(MethodDispatcher.class.getName());

    return md.getMethod(bop);
  }

  @Override
  public void handleFault(Message message) {

    Fault f = (Fault) message.getContent(Exception.class);

    log.error("WsInInterceptor::handleFault() - Code = " + f.getCode());
    log.error("WsInInterceptor::handleFault() - Msg = " + f.getMessage());

    Throwable cause = f.getCause();

    // TODO GENAPP obtenir Idioma de l'usuari aplicacio
    String language = "ca";

    log.error("WsInInterceptor::handleFault() - Cause = " + cause);
    if (cause != null) {
      log.error(
          "WsInInterceptor::handleFault() - Cause Class = " + cause.getClass());
      if (cause instanceof UndeclaredThrowableException) {
        log.error("WsInInterceptor::handleFault() - Cause.UndeclaredThrowable");
        cause = ((UndeclaredThrowableException) cause).getUndeclaredThrowable();
      }
      if (cause instanceof I18NException) {
        log.error("WsInInterceptor::handleFault() - CAUSE.I18NException");

        I18NException i18n = (I18NException) cause;
        String msg = I18NTranslatorWS.translate(i18n, new Locale(language));
        message.setContent(Exception.class,
            // new WsI18NException(i18n.getTraduccio(), msg, cause));
            new WsI18NException(WsUtils.convertToWsTranslation(i18n.getTraduccio()), msg,
                cause));
      } else if (cause instanceof I18NValidationException) {
        log.error("WsInInterceptor::handleFault() - CAUSE.ValidationException");
        I18NValidationException ve = (I18NValidationException) cause;
        message.setContent(Exception.class,
            WsUtils.convertToWsValidationException(ve, new Locale(language)));
      } else {
        log.error(
            "WsInInterceptor::handleFault() - Cause.msg = " + cause.getMessage());
        log.error(
            "WsInInterceptor::handleFault() - Cause.type = " + cause.getClass());
      }

    }
    super.handleFault(message);
  }

}