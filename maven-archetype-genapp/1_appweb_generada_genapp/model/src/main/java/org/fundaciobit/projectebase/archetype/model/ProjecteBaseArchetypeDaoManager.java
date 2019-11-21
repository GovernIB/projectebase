package org.fundaciobit.projectebase.archetype.model;

public class ProjecteBaseArchetypeDaoManager {
  
  private static IProjecteBaseArchetypeDaoManagers instance = null;
  
  public static void setDaoManagers(IProjecteBaseArchetypeDaoManagers managers) {
    instance = managers;
  }
  
  public static IProjecteBaseArchetypeDaoManagers getDaoManagers() throws Exception {
    if(instance == null) {
      throw new Exception("Ha de inicialitzar el sistema de Managers cridant "
          + " al mètode ProjecteBaseArchetypeDaoManager.setDaoManagers(...)");
    }
    return instance;
  }
  
}
