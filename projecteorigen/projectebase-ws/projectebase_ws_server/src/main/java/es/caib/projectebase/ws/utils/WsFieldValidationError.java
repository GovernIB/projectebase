package es.caib.projectebase.ws.utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author anadal
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "label", "field", "translation", "error" })
@XmlSeeAlso({WsI18NTranslation.class})
public class WsFieldValidationError {

  /** Etiqueta traduida del camp */
  @XmlElement(required = true)
  protected String label;

  /** Nom del camp java */
  @XmlElement(required = true)
  protected String field;
  
  /** Traducció completa de l'error */
  @XmlElement(required = true)
  protected String error;

  /** Codi d'error i arguments */
  @XmlElement(required = true)
  protected WsI18NTranslation translation;

  /**
   * 
   */
  public WsFieldValidationError() {
    super();
  }

  /**
   * @param field
   * @param error
   */
  public WsFieldValidationError(String field, String label,
      String error, WsI18NTranslation translation) {
    super();
    this.label = label;
    this.field = field;
    this.error = error;
    this.translation = translation;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public WsI18NTranslation getTranslation() {
    return translation;
  }

  public void setTranslation(WsI18NTranslation translation) {
    this.translation = translation;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  
  
}
