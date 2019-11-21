#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import ${package}.model.entity.Idioma;
import ${package}.jpa.IdiomaJPA;
import ${package}.jpa.IdiomaJPAManager;

@Stateless(name = "IdiomaEJB")
@SecurityDomain("seycon")
public class IdiomaEJB extends IdiomaJPAManager implements IdiomaLocal {

  @Override
	@RolesAllowed({"${prefixuppercase}_ADMIN","${prefixuppercase}_USER"})
	public void delete(Idioma instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"${prefixuppercase}_ADMIN","${prefixuppercase}_USER"})
	public Idioma create(Idioma instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"${prefixuppercase}_ADMIN","${prefixuppercase}_USER"})
	public Idioma update(Idioma instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"${prefixuppercase}_ADMIN","${prefixuppercase}_USER"})
  public IdiomaJPA findByPrimaryKey(String _ID_) {
    return (IdiomaJPA)super.findByPrimaryKey(_ID_);
  }

}
