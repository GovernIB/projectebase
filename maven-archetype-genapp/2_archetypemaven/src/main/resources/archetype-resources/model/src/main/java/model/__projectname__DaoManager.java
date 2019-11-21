#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model;

public class ${projectname}DaoManager {
  
  private static I${projectname}DaoManagers instance = null;
  
  public static void setDaoManagers(I${projectname}DaoManagers managers) {
    instance = managers;
  }
  
  public static I${projectname}DaoManagers getDaoManagers() throws Exception {
    if(instance == null) {
      throw new Exception("Ha de inicialitzar el sistema de Managers cridant "
          + " al mètode ${projectname}DaoManager.setDaoManagers(...)");
    }
    return instance;
  }
  
}
