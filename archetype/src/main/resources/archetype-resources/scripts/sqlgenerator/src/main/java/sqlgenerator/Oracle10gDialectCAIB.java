#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sqlgenerator;

import java.sql.Types;

/**
 * 
 * @author anadal
 *
 */
public class Oracle10gDialectCAIB extends org.hibernate.dialect.Oracle10gDialect {

  
  @Override
  protected void registerNumericTypeMappings() {
       super.registerNumericTypeMappings();  
       registerColumnType(Types.NUMERIC, "number" );
  }
  
  
}
