#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.commons.model;

/**
 * Classe utilitzada entre la cap d'EJB i les capes WEB, WS i REST
 * 
 * @author anadal
 *
 */
public class ModelSample {

  protected String data1;

  protected boolean data2;

  public String getData1() {
    return data1;
  }

  public ModelSample() {
    super();
  }

  public ModelSample(String data1, boolean data2) {
    super();
    this.data1 = data1;
    this.data2 = data2;
  }

  public void setData1(String data1) {
    this.data1 = data1;
  }

  public boolean isData2() {
    return data2;
  }

  public void setData2(boolean data2) {
    this.data2 = data2;
  }

}
