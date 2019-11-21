package org.fundaciobit.projectebase.archetype.logic;


/*
import org.fundaciobit.projectebase.archetype.ejb.AnnexEJB;
import org.fundaciobit.projectebase.archetype.ejb.FitxerLocal;
import org.fundaciobit.projectebase.archetype.jpa.AnnexJPA;
import org.fundaciobit.projectebase.archetype.model.entity.AnnexFirmat;
import org.fundaciobit.projectebase.archetype.model.fields.AnnexFields;
import org.fundaciobit.projectebase.archetype.model.fields.AnnexFirmatFields;

import org.fundaciobit.genapp.common.i18n.I18NException;

*/

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "SampleLogicaEJB")
@SecurityDomain("seycon")
public class SampleLogicaEJB implements SampleLogicaLocal {


}
 
 /**
@Stateless(name = "AnnexLogicaEJB")
@SecurityDomain("seycon")
public class AnnexLogicaEJB extends AnnexEJB implements AnnexLogicaLocal,
    AnnexFields {


  @EJB(mappedName = "projectebasearchetype/FitxerEJB/local")
  private FitxerLocal fitxerEjb;

  @EJB(mappedName = "projectebasearchetype/AnnexFirmatEJB/local")
  protected org.fundaciobit.projectebase.archetype.ejb.AnnexFirmatLocal annexFirmatEjb;
  
  
  @Override
  public AnnexJPA createFull(AnnexJPA annex) throws I18NException {
    // TODO Validar !!!
    
    return (AnnexJPA)create(annex);
  }
  


  @Override
  public Set<Long> deleteFull(AnnexJPA annex) throws I18NException {
    
    Set<Long> files = new HashSet<Long>();
    
    if (annex == null) {
      return files;
    }
    
    // Annex
    delete(annex);
    
    return files;
  }

}
*/
