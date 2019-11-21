
package org.fundaciobit.projectebase.archetype.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TraduccioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TraduccioQueryPath() {
  }

  protected TraduccioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField TRADUCCIOID() {
    return new LongField(getQueryPath(), TraduccioFields.TRADUCCIOID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TraduccioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


}
