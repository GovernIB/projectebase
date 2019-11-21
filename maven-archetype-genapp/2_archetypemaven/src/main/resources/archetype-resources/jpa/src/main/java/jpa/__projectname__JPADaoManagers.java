#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jpa;

import ${package}.model.*;
import ${package}.model.dao.*;
import javax.persistence.EntityManager;

public final class ${projectname}JPADaoManagers implements I${projectname}DaoManagers{

   private final FitxerJPAManager ${prefix}_fitxer;
   private final IdiomaJPAManager ${prefix}_idioma;
   private final TraduccioJPAManager ${prefix}_traduccio;

  public  ${projectname}JPADaoManagers(EntityManager __em) {
    this.${prefix}_fitxer = new FitxerJPAManager(__em);
    this.${prefix}_idioma = new IdiomaJPAManager(__em);
    this.${prefix}_traduccio = new TraduccioJPAManager(__em);
  }

	public IFitxerManager getFitxerManager() {
	  return this.${prefix}_fitxer;
	};

	public IIdiomaManager getIdiomaManager() {
	  return this.${prefix}_idioma;
	};

	public ITraduccioManager getTraduccioManager() {
	  return this.${prefix}_traduccio;
	};


}