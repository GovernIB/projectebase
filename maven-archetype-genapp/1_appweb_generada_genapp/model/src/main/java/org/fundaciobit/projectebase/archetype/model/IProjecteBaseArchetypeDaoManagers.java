package org.fundaciobit.projectebase.archetype.model;

import org.fundaciobit.projectebase.archetype.model.dao.*;

public interface IProjecteBaseArchetypeDaoManagers {
	public IFitxerManager getFitxerManager();
	public IIdiomaManager getIdiomaManager();
	public ITraduccioManager getTraduccioManager();

}