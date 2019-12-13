package es.caib.projectebase.commons.utils;

import java.io.File;

/**
 * 
 * @author anadal
 * 
 */
public class Configuration implements Constants {
 
  public static boolean isCAIB() {
    return Boolean.getBoolean(PROJECTEBASEARCHETYPE_PROPERTY_BASE + "iscaib");
  }

  public static File getFilesDirectory() {
    String path = System.getProperty(PROJECTEBASEARCHETYPE_PROPERTY_BASE + "filesdirectory");
    return new File(path);
  }
  
  public static String getFileSystemManagerClass() {
    return System.getProperty(PROJECTEBASEARCHETYPE_PROPERTY_BASE + "filesystemmanagerclass");
  }
  
  public static boolean isDesenvolupament() {
    return Boolean.getBoolean(PROJECTEBASEARCHETYPE_PROPERTY_BASE + "development");
  }


  public static String getAppUrl() {
    return System.getProperty(PROJECTEBASEARCHETYPE_PROPERTY_BASE + "url");
  }

  public static String getAppEmail() {
    return System.getProperty(PROJECTEBASEARCHETYPE_PROPERTY_BASE + "email.from");
  }

  public static String getDefaultLanguage() {
    return System.getProperty(PROJECTEBASEARCHETYPE_PROPERTY_BASE + "defaultlanguage", "ca");
  }


  public static byte[] getEncryptKey() {
    return System.getProperty(PROJECTEBASEARCHETYPE_PROPERTY_BASE + "encryptkey", "0123456789123456").getBytes();
  }

  public static Long getMaxUploadSizeInBytes() {
    return Long.getLong(PROJECTEBASEARCHETYPE_PROPERTY_BASE + "maxuploadsizeinbytes");
  }

  public static Long getMaxFitxerAdaptatSizeInBytes() {
    return Long.getLong(PROJECTEBASEARCHETYPE_PROPERTY_BASE + "maxfitxeradaptatsizeinbytes");
  }

}
