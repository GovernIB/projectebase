#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back;

import javax.faces.annotation.FacesConfig;

import static javax.faces.annotation.FacesConfig.Version.JSF_2_3;

/**
 * Necessari per activar la integració CDI amb JSF 2.3.
 * Permet injectar la majoria d'objectes de JSF.
 *
 * @author areus
 */
@FacesConfig(version = JSF_2_3)
public class ConfigurationBean {

}