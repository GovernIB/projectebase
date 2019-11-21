#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model;

import ${package}.model.dao.*;

public interface I${projectname}DaoManagers {
	public IFitxerManager getFitxerManager();
	public IIdiomaManager getIdiomaManager();
	public ITraduccioManager getTraduccioManager();

}