#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.ejb;

import javax.ejb.Local;

import ${package}.jpa.FitxerJPA;
import ${package}.model.dao.IFitxerManager;

@Local
public interface FitxerLocal extends IFitxerManager {

 public static final String JNDI_NAME = "${parentArtifactId}/FitxerEJB/local";
  public FitxerJPA findByPrimaryKey(Long _ID_);
}
