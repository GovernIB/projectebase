#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.ws.api.v1;

import java.sql.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import ${package}.ws.api.v1.utils.WsSqlDateAdapter;

public class Adapter2
    extends XmlAdapter<String, Date>
{


    public Date unmarshal(String value) {
        return (WsSqlDateAdapter.parseDate(value));
    }

    public String marshal(Date value) {
        return (WsSqlDateAdapter.printDate(value));
    }

}
