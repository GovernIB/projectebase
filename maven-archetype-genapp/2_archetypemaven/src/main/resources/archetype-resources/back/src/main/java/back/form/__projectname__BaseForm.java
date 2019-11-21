#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.form;

import org.fundaciobit.genapp.common.web.form.BaseForm;

/**
 * 
 * @author anadal
 *
 */
public abstract class ${projectname}BaseForm extends BaseForm {

  public ${projectname}BaseForm() {
  }
  
  public ${projectname}BaseForm(boolean nou) {
    super(nou);
  }
  
  public ${projectname}BaseForm(${projectname}BaseForm __toClone) {
    super(__toClone);
  }
  
}
