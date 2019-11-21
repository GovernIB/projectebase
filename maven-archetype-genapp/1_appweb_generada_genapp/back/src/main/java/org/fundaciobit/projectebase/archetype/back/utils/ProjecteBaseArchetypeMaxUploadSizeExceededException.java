package org.fundaciobit.projectebase.archetype.back.utils;

import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 
 * @author anadal
 *
 */
public class ProjecteBaseArchetypeMaxUploadSizeExceededException extends MaxUploadSizeExceededException {

  final String msgCode;

  /**
   * @param msg
   */
  public ProjecteBaseArchetypeMaxUploadSizeExceededException(Throwable cause, long maxSize, String msgCode) {
    super(maxSize, cause);    
    this.msgCode = msgCode;
  }

  public String getMsgCode() {
    return msgCode;
  }

}
