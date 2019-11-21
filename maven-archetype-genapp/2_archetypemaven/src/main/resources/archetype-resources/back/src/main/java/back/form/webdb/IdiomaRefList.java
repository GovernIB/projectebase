#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import ${package}.ejb.IdiomaLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import ${package}.model.fields.IdiomaFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class IdiomaRefList extends RefListBase
    implements IdiomaFields {

  @EJB(mappedName = IdiomaLocal.JNDI_NAME)
  private IdiomaLocal idiomaEjb;

  public IdiomaRefList(IdiomaRefList __clone) {
    super(__clone);
    this.idiomaEjb = __clone.idiomaEjb;
  }
  public IdiomaRefList() {
    setSelects(new Select<?>[] { NOM.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = idiomaEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
