package es.caib.projectebase.commons.query;

import java.io.Serializable;

/**
 * 
 * @author anadal
 *
 */
public class OrderBy implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -5856452637655410106L;

  public final OrderType orderType;

  public final String javaName;

  public OrderBy(String javaName, OrderType type) {
    this.javaName = javaName;
    this.orderType = type;
  }


  public OrderBy(String javaName) {
    super();
    this.javaName = javaName;
    this.orderType = OrderType.ASC;
  }

  public static String processOrderBy(OrderBy[] orderBy) {
    if (orderBy != null && orderBy.length > 0) {
      StringBuffer query = new StringBuffer();
      for (int i = 0; i < orderBy.length; i++) {
        if (orderBy[i]== null || orderBy[i].javaName == null 
          || orderBy[i].javaName.trim().length() ==0) {
          continue;
        }
        if (query.length() != 0) {
          query.append(", ");
        }
        query.append(orderBy[i].javaName).append(' ').append(orderBy[i].orderType);
      }     
      if (query.toString().trim().length() != 0) {
        return " order by " + query.toString();
      }
    } 
    return "";
  };

}
