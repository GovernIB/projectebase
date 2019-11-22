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

import ${package}.ejb.TraduccioLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import ${package}.model.fields.TraduccioFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TraduccioRefList extends RefListBase
    implements TraduccioFields {

  @EJB(mappedName = TraduccioLocal.JNDI_NAME)
  private TraduccioLocal traduccioEjb;

  public TraduccioRefList(TraduccioRefList __clone) {
    super(__clone);
    this.traduccioEjb = __clone.traduccioEjb;
  }
  public TraduccioRefList() {
    setSelects(new Select<?>[] { TRADUCCIOID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    List<${package}.model.entity.Traduccio> traduccions = traduccioEjb.select(where);
    List<StringKeyValue> _list = new java.util.ArrayList<StringKeyValue>(traduccions.size());
    final String _lang = org.fundaciobit.genapp.common.web.i18n.I18NUtils.getLocale().getLanguage();
    for (${package}.model.entity.Traduccio traduccio : traduccions) {
      ${package}.jpa.TraduccioJPA traduccioJPA = (${package}.jpa.TraduccioJPA) traduccio;
      String key = String.valueOf(traduccioJPA.getTraduccioID());
      String value = traduccioJPA.getTraduccio(_lang).getValor();
      StringKeyValue skv = new StringKeyValue(key, value);
      _list.add(skv);
    }
    java.util.Collections.sort(_list, new org.fundaciobit.genapp.common.KeyValue.KeyValueComparator<String>());
    return _list;

  }
}