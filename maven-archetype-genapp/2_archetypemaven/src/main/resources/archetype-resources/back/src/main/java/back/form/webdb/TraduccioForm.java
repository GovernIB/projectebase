#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.form.webdb;

import ${package}.back.form.${projectname}BaseForm;
import ${package}.jpa.TraduccioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TraduccioForm extends ${projectname}BaseForm {
  
  private TraduccioJPA traduccio;
  
  public TraduccioForm() {
  }
  
  public TraduccioForm(TraduccioForm __toClone) {
    super(__toClone);
      this.traduccio = __toClone.traduccio;
  }
  
  public TraduccioForm(TraduccioJPA traduccio, boolean nou) {
    super(nou);
    this.traduccio = traduccio;
  }
  
  public TraduccioJPA getTraduccio() {
    return traduccio;
  }
  public void setTraduccio(TraduccioJPA traduccio) {
    this.traduccio = traduccio;
  }
  
  
  
} // Final de Classe 
