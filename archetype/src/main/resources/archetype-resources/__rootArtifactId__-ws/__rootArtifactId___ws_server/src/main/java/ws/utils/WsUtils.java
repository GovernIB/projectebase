#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.commons.i18n.I18NArgument;
import ${package}.commons.i18n.I18NArgumentCode;
import ${package}.commons.i18n.I18NFieldError;
import ${package}.commons.i18n.I18NTranslation;
import ${package}.commons.i18n.I18NValidationException;


/**
 * 
 * @author anadal
 * 
 */
public class WsUtils {

  protected static final Logger log = LoggerFactory.getLogger(WsUtils.class);

  public static WsValidationException convertToWsValidationException(I18NValidationException ve,
      Locale locale) {
    if (ve == null) {
      return null;
    }

    StringBuffer str = new StringBuffer();
    List<WsFieldValidationError> list = new ArrayList<WsFieldValidationError>();

    for (I18NFieldError fe : ve.getFieldErrorList()) {
      I18NTranslation trans = fe.getTranslation();
      String code = trans.getCode();
      String[] args = I18NTranslatorWS.translateArguments(locale, trans.getArgs());
      String error = I18NTranslatorWS.translate(locale, code, args);
      String field = fe.getField();
      String fieldLabel = "Camp " + field; // TODO I18NTranslatorWS.translate(locale, field.fullName);

      list.add(new WsFieldValidationError(field, fieldLabel,
          error, convertToWsTranslation(trans)));

      if (str.length() != 0) {
        str.append("${symbol_escape}n");
      }
      str.append(fieldLabel + "(" + field + "): " + error);

    }

    return new WsValidationException(str.toString(), list);

  }
  
  
  /**
   * 
   * @param translation
   * @return
   */
  public static WsI18NTranslation convertToWsTranslation(I18NTranslation translation) {
    if (translation == null) {
      return null;
    }
    List<WsI18NArgument> args = null;
    I18NArgument[] origArgs = translation.getArgs();
    if (origArgs != null && origArgs.length != 0) {
      args = new ArrayList<WsI18NArgument>(origArgs.length);
      for (I18NArgument i18nArgument : origArgs) {
        args.add(new WsI18NArgument(i18nArgument.getValue(),
            i18nArgument instanceof I18NArgumentCode));
      }
    }
    return new WsI18NTranslation(translation.getCode(), args);

  }
  
  
  public static void printInfoUserApp(WebServiceContext wsContext) {

    try {

      log.info("WSUtils::printInfoUserApp()  Thread = "
          + Thread.currentThread().getId());

      

      HttpServletRequest servRequest = (HttpServletRequest) wsContext.getMessageContext().get(
          MessageContext.SERVLET_REQUEST);

      log.info("User = " + servRequest.getUserPrincipal().getName());

      // HttpSession session = (HttpSession) servRequest.getSession();
      // servletContext = session.getServletContext();

      /*
       * usuariAplicacioLogicaEjb
       * 
       * UsuariAplicacioJPA usuariAplicacio = (UsuariAplicacioJPA)
       * au.getDetails();
       * 
       * log.error("USUARI APLICACIO = " + usuariAplicacio);
       * 
       * log.error("USUARI APLICACIO FIND = " +
       * usuariAplicacioEjb.findByPrimaryKeyFull
       * (usuariAplicacio.getUsuariAplicacioID()));
       */
    } catch (Throwable e) {
      log.error("Error executant printInfoUserApp", e);
    }

  }

}
