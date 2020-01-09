#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )


@javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters( {
  @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value=${package}.ws.api.v1.utils.WsTimestampAdapter.class,type=java.sql.Timestamp.class),
  @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value=${package}.ws.api.v1.utils.WsSqlDateAdapter.class,type=java.sql.Date.class),
  @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value=${package}.ws.api.v1.utils.WsTimeAdapter.class,type=java.sql.Time.class)
})
package ${package}.model.bean;