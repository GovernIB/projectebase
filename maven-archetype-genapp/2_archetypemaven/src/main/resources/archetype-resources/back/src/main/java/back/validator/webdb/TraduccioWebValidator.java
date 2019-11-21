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
import ${package}.jpa.validator.TraduccioValidator;

import ${package}.back.form.webdb.TraduccioForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TraduccioWebValidator  implements Validator, TraduccioFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected TraduccioValidator<Object> validator = new TraduccioValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "${parentArtifactId}/TraduccioEJB/local")
  protected ${package}.ejb.TraduccioLocal traduccioEjb;



  public TraduccioWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return TraduccioForm.class.isAssignableFrom(clazz);
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
      isNou, traduccioEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public TraduccioValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(TraduccioValidator<Object> validator) {
    this.validator = validator;
  }

}