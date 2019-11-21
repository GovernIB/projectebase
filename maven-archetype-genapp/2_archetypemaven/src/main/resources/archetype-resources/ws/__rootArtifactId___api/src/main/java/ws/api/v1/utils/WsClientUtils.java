#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.api.v1.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ${package}.ws.api.v1.WsFieldValidationError;
import ${package}.ws.api.v1.WsI18NArgument;
import ${package}.ws.api.v1.WsI18NError;
import ${package}.ws.api.v1.WsI18NException;
import ${package}.ws.api.v1.WsI18NTranslation;
import ${package}.ws.api.v1.WsValidationException;

/**
 * 
 * @author anadal
 * 
 */
public class WsClientUtils {

  public static String toString(WsValidationException ve) {
    StringBuffer str = new StringBuffer();
    str.append(ve.getMessage()).append("${symbol_escape}n");
    int count = 1;
    for (WsFieldValidationError error : ve.getFaultInfo().getFieldFaults()) {
      str.append("  ------( " + count + " )-------${symbol_escape}n");
      str.append("  MESSA: " + error.getError() + "${symbol_escape}n");
      str.append("  FIELD: " + error.getField() + " (" + error.getLabel() + ")${symbol_escape}n");
      str.append("  TRANS: " + toString(error.getTranslation())).append("${symbol_escape}n");
      count++;
    }
    return str.toString();
  }

  public static String toString(WsI18NException i18ne) {
    WsI18NError error = i18ne.getFaultInfo();

    StringBuffer strAll = new StringBuffer();

    strAll.append("MESSAGE: ").append(i18ne.getMessage()).append("${symbol_escape}n");
    strAll.append("TRANSLA: ").append(toString(error.getTranslation())).append("${symbol_escape}n");

    return strAll.toString();

  }

  public static String toString(WsI18NTranslation trans) {
    StringBuffer strAll = new StringBuffer(trans.getCode());

    if (trans.getArgs() != null && trans.getArgs().size() != 0) {
      List<String> str = new ArrayList<String>();
      for (WsI18NArgument arg : trans.getArgs()) {
        str.add((arg.isTranslate() ? "*" : "") + arg.getValue());
      }
      strAll.append(" " + Arrays.toString(str.toArray()));
    }

    return strAll.toString();
  }

}
