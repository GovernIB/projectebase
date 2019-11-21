#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.form.webdb;

import ${package}.back.form.${projectname}BaseForm;
import ${package}.jpa.FitxerJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class FitxerForm extends ${projectname}BaseForm {
  
  private FitxerJPA fitxer;
  
  public FitxerForm() {
  }
  
  public FitxerForm(FitxerForm __toClone) {
    super(__toClone);
      this.fitxer = __toClone.fitxer;
  }
  
  public FitxerForm(FitxerJPA fitxer, boolean nou) {
    super(nou);
    this.fitxer = fitxer;
  }
  
  public FitxerJPA getFitxer() {
    return fitxer;
  }
  public void setFitxer(FitxerJPA fitxer) {
    this.fitxer = fitxer;
  }
  
  
  
} // Final de Classe 
