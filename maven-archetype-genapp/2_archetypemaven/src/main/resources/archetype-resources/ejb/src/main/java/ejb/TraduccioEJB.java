#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import ${package}.model.entity.Traduccio;
import ${package}.jpa.TraduccioJPA;
import ${package}.jpa.TraduccioJPAManager;

@Stateless(name = "TraduccioEJB")
@SecurityDomain("seycon")
public class TraduccioEJB extends TraduccioJPAManager implements TraduccioLocal {

  @Override
	@RolesAllowed({"${prefixuppercase}_ADMIN","${prefixuppercase}_USER"})
	public void delete(Traduccio instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"${prefixuppercase}_ADMIN","${prefixuppercase}_USER"})
	public Traduccio create(Traduccio instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"${prefixuppercase}_ADMIN","${prefixuppercase}_USER"})
	public Traduccio update(Traduccio instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"${prefixuppercase}_ADMIN","${prefixuppercase}_USER"})
  public TraduccioJPA findByPrimaryKey(Long _ID_) {
    return (TraduccioJPA)super.findByPrimaryKey(_ID_);
  }

}
