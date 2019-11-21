
package org.fundaciobit.projectebase.archetype.ejb;

import javax.ejb.Local;

import org.fundaciobit.projectebase.archetype.jpa.TraduccioJPA;
import org.fundaciobit.projectebase.archetype.model.dao.ITraduccioManager;

@Local
public interface TraduccioLocal extends ITraduccioManager {

 public static final String JNDI_NAME = "projectebasearchetype/TraduccioEJB/local";
  public TraduccioJPA findByPrimaryKey(Long _ID_);
}
