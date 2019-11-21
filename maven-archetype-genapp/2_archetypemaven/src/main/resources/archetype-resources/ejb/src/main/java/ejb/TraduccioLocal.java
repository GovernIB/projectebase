#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.ejb;

import javax.ejb.Local;

import ${package}.jpa.TraduccioJPA;
import ${package}.model.dao.ITraduccioManager;

@Local
public interface TraduccioLocal extends ITraduccioManager {

 public static final String JNDI_NAME = "${parentArtifactId}/TraduccioEJB/local";
  public TraduccioJPA findByPrimaryKey(Long _ID_);
}
