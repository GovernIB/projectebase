#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jpa.validator;

import ${package}.jpa.FitxerJPA;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import java.util.List;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.validation.AbstractBeanValidator;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class FitxerBeanValidator 
      extends AbstractBeanValidator<FitxerJPA> {


  // EJB's
  protected final ${package}.model.dao.IFitxerManager __fitxerManager;


  public final FitxerValidator<FitxerJPA> _validator;


  public FitxerBeanValidator(${package}.model.dao.IFitxerManager __fitxerManager) { 
    this.__fitxerManager = __fitxerManager;
    _validator = new FitxerValidator<FitxerJPA>();
  }

  public FitxerBeanValidator(FitxerValidator<FitxerJPA> _validator,
     ${package}.model.dao.IFitxerManager __fitxerManager) {
    this.__fitxerManager = __fitxerManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(FitxerJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<FitxerJPA> _bvr_ = new BeanValidatorResult<FitxerJPA>();
    _validator.validate(_bvr_, target, isNou, __fitxerManager);
    return _bvr_.getErrors();
  }
}
