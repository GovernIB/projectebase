
package org.fundaciobit.projectebase.archetype.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.projectebase.archetype.model.entity.Idioma;
import org.fundaciobit.projectebase.archetype.jpa.IdiomaJPA;
import org.fundaciobit.projectebase.archetype.jpa.IdiomaJPAManager;

@Stateless(name = "IdiomaEJB")
@SecurityDomain("seycon")
public class IdiomaEJB extends IdiomaJPAManager implements IdiomaLocal {

  @Override
	@RolesAllowed({"PBS_ADMIN","PBS_USER"})
	public void delete(Idioma instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PBS_ADMIN","PBS_USER"})
	public Idioma create(Idioma instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PBS_ADMIN","PBS_USER"})
	public Idioma update(Idioma instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PBS_ADMIN","PBS_USER"})
  public IdiomaJPA findByPrimaryKey(String _ID_) {
    return (IdiomaJPA)super.findByPrimaryKey(_ID_);
  }

}
