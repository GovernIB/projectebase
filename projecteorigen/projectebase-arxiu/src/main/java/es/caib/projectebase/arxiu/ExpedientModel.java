/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.caib.projectebase.arxiu;

import javax.enterprise.inject.Model;

/**
 *
 * @author Antoni
 */
@Model
public class ExpedientModel {
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
