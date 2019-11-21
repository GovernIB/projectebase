
package org.fundaciobit.projectebase.archetype.ejb;

import javax.ejb.Local;

import org.fundaciobit.projectebase.archetype.jpa.FitxerJPA;
import org.fundaciobit.projectebase.archetype.model.dao.IFitxerManager;

@Local
public interface FitxerLocal extends IFitxerManager {

 public static final String JNDI_NAME = "projectebasearchetype/FitxerEJB/local";
  public FitxerJPA findByPrimaryKey(Long _ID_);
}
