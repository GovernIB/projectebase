package org.fundaciobit.projectebase.archetype.back.controller;

import org.fundaciobit.projectebase.archetype.ejb.FitxerLocal;
import org.fundaciobit.projectebase.archetype.model.entity.Fitxer;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.IGenAppEntity;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.web.controller.CommonFilesBaseController;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.genapp.common.web.form.BaseForm;
import org.springframework.stereotype.Controller;

import javax.ejb.EJB;

/**
 * POT SOBRESCRIURE AQUESTA CLASSE
 * @author anadal
 * 
 */
@Controller
public abstract class ProjecteBaseArchetypeFilesBaseController<I extends IGenAppEntity, PK extends Object, F extends BaseForm>
  extends CommonFilesBaseController<I, PK, F, Fitxer> {

  @EJB(mappedName = "projectebasearchetype/FitxerEJB/local")
  protected FitxerLocal fitxerEjb;

  protected final Logger log = Logger.getLogger(getClass());

  /**
   * 
   * @return
   */
  protected FilesFormManager<Fitxer> getFilesFormManager() {
    return new ProjecteBaseArchetypeFilesFormManager(fitxerEjb);
  }

  /**
   * 
   * @param arxiu
   * @return
   */
  public boolean deleteFile(Long fileID) {
    if (fileID != null) {
      Fitxer file = null;
      try {
        file = fitxerEjb.findByPrimaryKey(fileID);
        if (file != null) {
          fitxerEjb.delete(file);
        }
      } catch (Exception e) {
        log.error("Error borrant arxiu fisic amb id=" + fileID +
            ((file == null)? "" : ("("+ file.getNom() + ")")));
      }

      return FileSystemManager.eliminarArxiu(fileID);
    }
    return true;
  }


}
