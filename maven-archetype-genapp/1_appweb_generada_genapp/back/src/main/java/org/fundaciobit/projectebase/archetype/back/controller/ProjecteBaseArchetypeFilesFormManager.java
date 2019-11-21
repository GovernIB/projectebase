package org.fundaciobit.projectebase.archetype.back.controller;

import org.fundaciobit.projectebase.archetype.jpa.FitxerJPA;
import org.fundaciobit.projectebase.archetype.model.entity.Fitxer;

import org.fundaciobit.genapp.common.filesystem.IFileManager;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;


/**
 * Gestiona Multiples Fitxers d'un Form
 * 
 * @author anadal
 * 
 */
public class ProjecteBaseArchetypeFilesFormManager extends FilesFormManager<Fitxer> {

  public ProjecteBaseArchetypeFilesFormManager(IFileManager<Fitxer, Long> fitxerEjb) {
    super(fitxerEjb);
  }

  @Override
  public FitxerJPA createEmptyFile() {    
    return new FitxerJPA();
  }

}
