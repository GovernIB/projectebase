#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.ws.api.v1;

import java.sql.Timestamp;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import ${package}.ws.api.v1.utils.WsTimestampAdapter;



public class Adapter1
    extends XmlAdapter<String, Timestamp>
{


    public Timestamp unmarshal(String value) {
        return (WsTimestampAdapter.parseDateTime(value));
    }

    public String marshal(Timestamp value) {
        return (WsTimestampAdapter.printDateTime(value));
    }

}
