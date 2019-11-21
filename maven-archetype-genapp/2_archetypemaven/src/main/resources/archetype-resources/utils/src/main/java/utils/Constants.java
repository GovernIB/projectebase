#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.utils;


/**
 *
 * @author anadal
 *
 */
public interface Constants {

  public static final String ${projectnameuppercase}_PROPERTY_BASE = "${package}.";

  public static final String SECURITY_DOMAIN = "seycon";

  public static final String MAIL_SERVICE = "java:/${package}.mail";

  public static final String MAIL_QUEUE = "jms/${projectname}Queue";
  
  public static final String ${prefixuppercase}_ADMIN = "${prefixuppercase}_ADMIN";

  public static final String ${prefixuppercase}_USER = "${prefixuppercase}_USER";
  
  // ROLE ADMIN 
  public static final String ROLE_ADMIN = "ROLE_ADMIN";
  // ROLE USER
  public static final String ROLE_USER = "ROLE_USER";  

}
