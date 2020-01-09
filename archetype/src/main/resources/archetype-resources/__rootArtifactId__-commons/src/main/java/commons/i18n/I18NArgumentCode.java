#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.commons.i18n;

/**
 * 
 * @author anadal
 *
 */
public class I18NArgumentCode implements I18NArgument {

  public final String code;

  /**
   * @param code
   */
  public I18NArgumentCode(String code) {
    super();
    this.code = code;
  }

  public String getValue() {
    return this.code;
  }

}
