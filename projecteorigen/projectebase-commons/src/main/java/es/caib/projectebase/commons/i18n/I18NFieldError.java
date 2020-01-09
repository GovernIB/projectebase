package es.caib.projectebase.commons.i18n;


/**
 * 
 * @author anadal
 * 
 */
public class I18NFieldError {

  String field;

  I18NTranslation translation;

  /**
   * 
   */
  public I18NFieldError() {
  }

  /**
   * @param field
   * @param translation
   */
  public I18NFieldError(String field, I18NTranslation translation) {
    super();
    this.field = field;
    this.translation = translation;
  }

  public String getField() {
    return field;
  }

  public I18NTranslation getTranslation() {
    return translation;
  }

  public void setField(String field) {
    this.field = field;
  }

  public void setTranslation(I18NTranslation translation) {
    this.translation = translation;
  }

}
