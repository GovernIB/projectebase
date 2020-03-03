
package es.caib.projectebase.ws.api.v1;

import java.sql.Time;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import es.caib.projectebase.ws.api.v1.utils.WsTimeAdapter;

public class Adapter3
        extends XmlAdapter<String, Time> {


    public Time unmarshal(String value) {
        return (WsTimeAdapter.parseTime(value));
    }

    public String marshal(Time value) {
        return (WsTimeAdapter.printTime(value));
    }

}
