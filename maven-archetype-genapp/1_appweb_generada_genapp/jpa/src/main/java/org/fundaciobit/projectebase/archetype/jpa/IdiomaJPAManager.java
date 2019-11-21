
package org.fundaciobit.projectebase.archetype.jpa;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.projectebase.archetype.model.entity.*;
import org.fundaciobit.projectebase.archetype.model.fields.*;
import org.fundaciobit.projectebase.archetype.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.events.ModificationManager;


public class IdiomaJPAManager
		 extends AbstractProjecteBaseArchetypeJPAManager<Idioma, String>
		 implements IIdiomaManager, IdiomaFields {




  private static final long serialVersionUID = 338419239L;

	 public static final TableName<Idioma> _TABLENAME =  new TableName<Idioma>("IdiomaJPA");



  static final ModificationManager<Idioma> __eventManager = new ModificationManager<Idioma>();


  @PersistenceContext
  protected EntityManager __em;
  public IdiomaJPAManager() {
  }
  protected IdiomaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return IdiomaJPA. class;
	}



	public ModificationManager<Idioma> getEventManager() {
	return __eventManager;
	}


	public TableName<Idioma> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Idioma[] listToArray(List<Idioma> list)  {
		if(list == null) { return null; };
		return list.toArray(new Idioma[list.size()]);
	};

	public synchronized Idioma create( java.lang.String _idiomaID_, java.lang.String _nom_, boolean _suportat_, int _ordre_) throws I18NException {
		IdiomaJPA __bean =  new IdiomaJPA(_idiomaID_,_nom_,_suportat_,_ordre_);
		return create(__bean);
	}



 public void delete(java.lang.String _idiomaID_) {
   delete(findByPrimaryKey(_idiomaID_));
 }




	public Idioma findByPrimaryKey(java.lang.String _idiomaID_) {
	  return __em.find(IdiomaJPA.class, _idiomaID_);  
	}
	@Override
	protected Idioma getJPAInstance(Idioma __bean) {
		return convertToJPA(__bean);
	}


	public static IdiomaJPA convertToJPA(Idioma __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof IdiomaJPA) {
	    return (IdiomaJPA)__bean;
	  }
	  
	  return IdiomaJPA.toJPA(__bean);
	}


}