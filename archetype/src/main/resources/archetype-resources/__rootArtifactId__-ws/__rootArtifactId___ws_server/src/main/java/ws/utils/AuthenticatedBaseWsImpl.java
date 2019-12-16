#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.utils;
/*
import java.util.List;

import javax.activation.DataHandler;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;

import org.fundaciobit.genapp.common.ws.WsI18NException;
import org.fundaciobit.genapp.common.ws.WsValidationException;


import ${package}.hibernate.HibernateFileUtil;
import ${package}.jpa.FitxerJPA;
import ${package}.model.bean.FitxerBean;
import ${package}.model.entity.Fitxer;
import ${package}.model.fields.FitxerFields;
import ${package}.ejb.FitxerLocal;
*/

/**
 * 
 * @author anadal
 * 
 */
public class AuthenticatedBaseWsImpl extends BaseWsImpl  {

  
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // --------------------------| UTILITATS |----------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
/*
  @EJB(mappedName = FitxerLocal.JNDI_NAME)
  protected FitxerLocal fitxerEjb;
  
  @RolesAllowed({ ${prefixuppercase}_ADMIN, ${prefixuppercase}_USER })
  @WebMethod
  public FitxerBean downloadFileUsingEncryptedFileID(String encryptedFileID)
      throws WsI18NException, Throwable {

    FitxerJPA.enableEncryptedFileIDGeneration();
    try {
      long fitxerID = HibernateFileUtil.decryptFileID(encryptedFileID);

      // Checks
      Fitxer fitxer = checkBasic(fitxerID);

      FitxerBean fitxerBean = new FitxerBean(fitxer);
      
      byte[] data = FileUtils.readFileToByteArray(FileSystemManager.getFile(fitxerID));

      DataHandler dh = new DataHandler(new ByteArrayDataSource(data, fitxerBean.getMime()));

      fitxerBean.setData(dh);

      return fitxerBean;

    } finally {
      FitxerJPA.disableEncryptedFileIDGeneration();
    }

  }
  
  
  
  public FitxerJPA checkBasic(long fitxerID) throws I18NException {
    if (fitxerID == 0) {
      // error.notfound=No s'ha trobat cap {0} amb {1} igual a {2}
      throw new I18NException("error.notfound",
          new I18NArgumentCode(_TABLE_TRANSLATION),
          new I18NArgumentCode(FITXERID.fullName),
          new I18NArgumentString(String.valueOf(fitxerID))
          );
    }
    
    FitxerJPA ua = (FitxerJPA)fitxerEjb.findByPrimaryKey(fitxerID);
    if (ua == null) {
      // error.notfound=No s'ha trobat cap {0} amb {1} igual a {2}
      throw new I18NException("error.notfound",
          new I18NArgumentCode(_TABLE_TRANSLATION),
          new I18NArgumentCode(FITXERID.fullName),
          new I18NArgumentString(String.valueOf(fitxerID))
          );
    }
    
    return ua;
  }
*/
}
