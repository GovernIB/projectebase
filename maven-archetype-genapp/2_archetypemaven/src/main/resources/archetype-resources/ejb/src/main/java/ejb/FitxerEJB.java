#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import ${package}.model.entity.Fitxer;
import ${package}.jpa.FitxerJPA;
import ${package}.jpa.FitxerJPAManager;

@Stateless(name = "FitxerEJB")
@SecurityDomain("seycon")
public class FitxerEJB extends FitxerJPAManager implements FitxerLocal {

  @Override
	@RolesAllowed({"${prefixuppercase}_ADMIN","${prefixuppercase}_USER"})
	public void delete(Fitxer instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"${prefixuppercase}_ADMIN","${prefixuppercase}_USER"})
	public Fitxer create(Fitxer instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"${prefixuppercase}_ADMIN","${prefixuppercase}_USER"})
	public Fitxer update(Fitxer instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"${prefixuppercase}_ADMIN","${prefixuppercase}_USER"})
  public FitxerJPA findByPrimaryKey(Long _ID_) {
    return (FitxerJPA)super.findByPrimaryKey(_ID_);
  }

}
