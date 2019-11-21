#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.form.webdb;

import ${package}.back.form.${projectname}BaseForm;
import ${package}.jpa.IdiomaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class IdiomaForm extends ${projectname}BaseForm {
  
  private IdiomaJPA idioma;
  
  public IdiomaForm() {
  }
  
  public IdiomaForm(IdiomaForm __toClone) {
    super(__toClone);
      this.idioma = __toClone.idioma;
  }
  
  public IdiomaForm(IdiomaJPA idioma, boolean nou) {
    super(nou);
    this.idioma = idioma;
  }
  
  public IdiomaJPA getIdioma() {
    return idioma;
  }
  public void setIdioma(IdiomaJPA idioma) {
    this.idioma = idioma;
  }
  
  
  
} // Final de Classe 
