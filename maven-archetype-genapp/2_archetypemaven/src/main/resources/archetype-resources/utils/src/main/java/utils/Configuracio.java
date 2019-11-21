#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.utils;

import java.io.File;

/**
 * 
 * @author anadal
 * 
 */
public class Configuracio implements Constants {
 
  public static boolean isCAIB() {
    return Boolean.getBoolean(${projectnameuppercase}_PROPERTY_BASE + "iscaib");
  }

  public static File getFilesDirectory() {
    String path = System.getProperty(${projectnameuppercase}_PROPERTY_BASE + "filesdirectory");
    return new File(path);
  }
  
  public static boolean isDesenvolupament() {
    return Boolean.getBoolean(${projectnameuppercase}_PROPERTY_BASE + "development");
  }


  public static String getAppUrl() {
    return System.getProperty(${projectnameuppercase}_PROPERTY_BASE + "url");
  }

  public static String getAppEmail() {
    return System.getProperty(${projectnameuppercase}_PROPERTY_BASE + "email.from");
  }

  public static String getAppName() {
    return System.getProperty(${projectnameuppercase}_PROPERTY_BASE + "name", "${projectname}");
  }

  public static String getDefaultLanguage() {
    return System.getProperty(${projectnameuppercase}_PROPERTY_BASE + "defaultlanguage", "ca");
  }


  public static byte[] getEncryptKey() {
    return System.getProperty(${projectnameuppercase}_PROPERTY_BASE + "encryptkey", "0123456789123456").getBytes();
  }

  public static Long getMaxUploadSizeInBytes() {
    return Long.getLong(${projectnameuppercase}_PROPERTY_BASE + "maxuploadsizeinbytes");
  }

  public static Long getMaxFitxerAdaptatSizeInBytes() {
    return Long.getLong(${projectnameuppercase}_PROPERTY_BASE + "maxfitxeradaptatsizeinbytes");
  }

}
