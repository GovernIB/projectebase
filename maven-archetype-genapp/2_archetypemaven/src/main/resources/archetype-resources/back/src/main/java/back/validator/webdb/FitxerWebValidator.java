#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import ${package}.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ${package}.jpa.validator.FitxerValidator;

import ${package}.back.form.webdb.FitxerForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class FitxerWebValidator  implements Validator, FitxerFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected FitxerValidator<Object> validator = new FitxerValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "${parentArtifactId}/FitxerEJB/local")
  protected ${package}.ejb.FitxerLocal fitxerEjb;



  public FitxerWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return FitxerForm.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    WebValidationResult<Object> wvr;
    wvr = new WebValidationResult<Object>(errors);

    Boolean nou = (Boolean)errors.getFieldValue("nou");
    boolean isNou =  nou != null && nou.booleanValue();

    validate(target, errors, wvr, isNou);
  }


  public void validate(Object target, Errors errors,
    WebValidationResult<Object> wvr, boolean isNou) {

    validator.validate(wvr, target,
      isNou, fitxerEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public FitxerValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(FitxerValidator<Object> validator) {
    this.validator = validator;
  }

}