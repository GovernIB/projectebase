#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.utils;

import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 
 * @author anadal
 *
 */
public class ${projectname}MaxUploadSizeExceededException extends MaxUploadSizeExceededException {

  final String msgCode;

  /**
   * @param msg
   */
  public ${projectname}MaxUploadSizeExceededException(Throwable cause, long maxSize, String msgCode) {
    super(maxSize, cause);    
    this.msgCode = msgCode;
  }

  public String getMsgCode() {
    return msgCode;
  }

}
