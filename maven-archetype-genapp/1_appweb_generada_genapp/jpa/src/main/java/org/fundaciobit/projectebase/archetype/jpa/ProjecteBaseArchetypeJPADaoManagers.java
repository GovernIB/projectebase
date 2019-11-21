package org.fundaciobit.projectebase.archetype.jpa;

import org.fundaciobit.projectebase.archetype.model.*;
import org.fundaciobit.projectebase.archetype.model.dao.*;
import javax.persistence.EntityManager;

public final class ProjecteBaseArchetypeJPADaoManagers implements IProjecteBaseArchetypeDaoManagers{

   private final FitxerJPAManager pbs_fitxer;
   private final IdiomaJPAManager pbs_idioma;
   private final TraduccioJPAManager pbs_traduccio;

  public  ProjecteBaseArchetypeJPADaoManagers(EntityManager __em) {
    this.pbs_fitxer = new FitxerJPAManager(__em);
    this.pbs_idioma = new IdiomaJPAManager(__em);
    this.pbs_traduccio = new TraduccioJPAManager(__em);
  }

	public IFitxerManager getFitxerManager() {
	  return this.pbs_fitxer;
	};

	public IIdiomaManager getIdiomaManager() {
	  return this.pbs_idioma;
	};

	public ITraduccioManager getTraduccioManager() {
	  return this.pbs_traduccio;
	};


}