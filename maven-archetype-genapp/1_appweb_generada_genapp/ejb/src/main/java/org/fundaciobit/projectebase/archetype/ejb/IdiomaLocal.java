
package org.fundaciobit.projectebase.archetype.ejb;

import javax.ejb.Local;

import org.fundaciobit.projectebase.archetype.jpa.IdiomaJPA;
import org.fundaciobit.projectebase.archetype.model.dao.IIdiomaManager;

@Local
public interface IdiomaLocal extends IIdiomaManager {

 public static final String JNDI_NAME = "projectebasearchetype/IdiomaEJB/local";
  public IdiomaJPA findByPrimaryKey(String _ID_);
}
