#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.apifirmasimple.example.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
public class IndexController {

    @RequestMapping(value = { "", "/", "/index.html", "/index.jsp" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {

        return new ModelAndView("index");
    }

}
