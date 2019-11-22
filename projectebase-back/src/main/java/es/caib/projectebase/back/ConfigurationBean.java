package es.caib.projectebase.back;

import javax.faces.annotation.FacesConfig;

import static javax.faces.annotation.FacesConfig.Version.JSF_2_3;

/**
 * Activar la integració CDI amb JSF 2.3.
 * Permet injectar la majoria d'objectes de JSF.
 */
@FacesConfig(
        // Activates CDI build-in beans
        version = JSF_2_3
)
public class ConfigurationBean {

}