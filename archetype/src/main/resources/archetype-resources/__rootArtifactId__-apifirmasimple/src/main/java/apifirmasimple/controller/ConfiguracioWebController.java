#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.apifirmasimple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/web")
@SessionAttributes(types = { ${package}.apifirmasimple.form.FirmaForm.class })
public class ConfiguracioWebController extends AbstractConfiguracioController {

    @Override
    public boolean isWeb() {
        return true;
    }

}
