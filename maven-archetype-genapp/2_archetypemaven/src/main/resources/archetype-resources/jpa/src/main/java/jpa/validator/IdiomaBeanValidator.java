#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jpa.validator;

import ${package}.jpa.IdiomaJPA;
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
public class IdiomaBeanValidator 
      extends AbstractBeanValidator<IdiomaJPA> {


  // EJB's
  protected final ${package}.model.dao.IIdiomaManager __idiomaManager;


  public final IdiomaValidator<IdiomaJPA> _validator;


  public IdiomaBeanValidator(${package}.model.dao.IIdiomaManager __idiomaManager) { 
    this.__idiomaManager = __idiomaManager;
    _validator = new IdiomaValidator<IdiomaJPA>();
  }

  public IdiomaBeanValidator(IdiomaValidator<IdiomaJPA> _validator,
     ${package}.model.dao.IIdiomaManager __idiomaManager) {
    this.__idiomaManager = __idiomaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(IdiomaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<IdiomaJPA> _bvr_ = new BeanValidatorResult<IdiomaJPA>();
    _validator.validate(_bvr_, target, isNou, __idiomaManager);
    return _bvr_.getErrors();
  }
}