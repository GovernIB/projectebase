package org.fundaciobit.projectebase.archetype.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.projectebase.archetype.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.projectebase.archetype.jpa.validator.IdiomaValidator;

import org.fundaciobit.projectebase.archetype.back.form.webdb.IdiomaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class IdiomaWebValidator  implements Validator, IdiomaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected IdiomaValidator<Object> validator = new IdiomaValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "projectebasearchetype/IdiomaEJB/local")
  protected org.fundaciobit.projectebase.archetype.ejb.IdiomaLocal idiomaEjb;



  public IdiomaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return IdiomaForm.class.isAssignableFrom(clazz);
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
      isNou, idiomaEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public IdiomaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(IdiomaValidator<Object> validator) {
    this.validator = validator;
  }

}