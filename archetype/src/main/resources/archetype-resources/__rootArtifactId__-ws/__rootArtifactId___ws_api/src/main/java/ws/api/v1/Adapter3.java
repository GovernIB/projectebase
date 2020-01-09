#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.ws.api.v1;

import java.sql.Time;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import ${package}.ws.api.v1.utils.WsTimeAdapter;

public class Adapter3
    extends XmlAdapter<String, Time>
{


    public Time unmarshal(String value) {
        return (WsTimeAdapter.parseTime(value));
    }

    public String marshal(Time value) {
        return (WsTimeAdapter.printTime(value));
    }

}
