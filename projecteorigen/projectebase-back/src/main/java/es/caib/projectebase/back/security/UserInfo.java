package es.caib.projectebase.back.security;

import javax.enterprise.inject.Vetoed;
import java.io.Serializable;

/**
 * Encapçula la informació d'un usuari.
 * Està marcada amb @Vetoed per impedir que CDI la instancii directament. Es generarà amb el producer corresponent.
 * 
 * @author areus
 */
@Vetoed
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private final String username;
    private final String nom;
    private final String llinatges;
    private final String email;
    private final String nif;

    public UserInfo() {
        this.username = null;
        this.nom = null;
        this.llinatges = null;
        this.email = null;
        this.nif = null;
    }

    public UserInfo(String username, String nom, String llinatges, String email, String nif) {
        this.username = username;
        this.nom = nom;
        this.llinatges = llinatges;
        this.email = email;
        this.nif = nif;
    }

    public String getUsername() {
        return username;
    }

    public String getNom() {
        return nom;
    }

    public String getLlinatges() {
        return llinatges;
    }

    public String getEmail() {
        return email;
    }

    public String getNif() {
        return nif;
    }
}
