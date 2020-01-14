

@javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters( {
  @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value=es.caib.projectebase.ws.api.v1.utils.WsTimestampAdapter.class,type=java.sql.Timestamp.class),
  @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value=es.caib.projectebase.ws.api.v1.utils.WsSqlDateAdapter.class,type=java.sql.Date.class),
  @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value=es.caib.projectebase.ws.api.v1.utils.WsTimeAdapter.class,type=java.sql.Time.class)
})
package es.caib.projectebase.persistence;