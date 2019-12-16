#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )


@javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters( {
  @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value=org.fundaciobit.genapp.common.ws.WsTimestampAdapter.class,type=java.sql.Timestamp.class),
  @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value=org.fundaciobit.genapp.common.ws.WsSqlDateAdapter.class,type=java.sql.Date.class),
  @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value=org.fundaciobit.genapp.common.ws.WsTimeAdapter.class,type=java.sql.Time.class)
})
package ${package}.jpa;