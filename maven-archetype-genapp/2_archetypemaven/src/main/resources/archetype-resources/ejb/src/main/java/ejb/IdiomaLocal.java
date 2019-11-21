#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.ejb;

import javax.ejb.Local;

import ${package}.jpa.IdiomaJPA;
import ${package}.model.dao.IIdiomaManager;

@Local
public interface IdiomaLocal extends IIdiomaManager {

 public static final String JNDI_NAME = "${parentArtifactId}/IdiomaEJB/local";
  public IdiomaJPA findByPrimaryKey(String _ID_);
}
