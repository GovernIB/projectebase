package es.caib.projectebase.sqlgenerator;

import java.sql.Types;

/**
 * 
 * @author anadal
 *
 */
public class Oracle9iDialectCAIB extends org.hibernate.dialect.Oracle9iDialect {

  
  @Override
  protected void registerNumericTypeMappings() {
       super.registerNumericTypeMappings();  
       registerColumnType(Types.NUMERIC, "number" );
  }
  
  
}
