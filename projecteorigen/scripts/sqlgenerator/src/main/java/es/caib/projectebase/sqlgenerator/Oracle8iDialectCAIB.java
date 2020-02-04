package es.caib.projectebase.sqlgenerator;

import java.sql.Types;

/**
 * 
 * @author anadal
 *
 */
public class Oracle8iDialectCAIB extends org.hibernate.dialect.Oracle8iDialect {

  
  @Override
  protected void registerNumericTypeMappings() {
       super.registerNumericTypeMappings();  
       registerColumnType(Types.NUMERIC, "number" );
  }
  
  
}
