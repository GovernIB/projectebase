package es.caib.projectebase.arxiu;

import javax.enterprise.inject.Model;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author Antoni
 */
@Model
public class ExpedientModel {
    
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
