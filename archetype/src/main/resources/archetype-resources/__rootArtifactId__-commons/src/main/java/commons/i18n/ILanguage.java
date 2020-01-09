#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.commons.i18n;

public interface ILanguage {

  public java.lang.String getIdiomaID();

  public java.lang.String getNom();

  public boolean isSuportat();
}
