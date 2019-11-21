package org.fundaciobit.projectebase.archetype.back.controller.common;

import org.fundaciobit.projectebase.archetype.utils.Configuracio;
import org.fundaciobit.projectebase.archetype.utils.Constants;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Map;

/**
 *  
 * @autor anadal
 * 
 */
@Controller
public class PrincipalController {

  protected final Logger log = Logger.getLogger(getClass());

  @RequestMapping(value = "/common/principal.html")
  public ModelAndView principal(HttpSession session,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    
    Boolean initialized = (Boolean)session.getAttribute("inicialitzat");
    
    if (initialized == null) {
      HtmlUtils.saveMessageInfo(request, "MessageInfo : Benvingut a ProjecteBaseArchetype");      
      session.setAttribute("inicialitzat", true);
    }

    return new ModelAndView("principal");

  }

  @RequestMapping(value = "/canviarPipella", method = RequestMethod.GET)
  public ModelAndView canviarPipella(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    return canviarPipella(request, response, null);
  }

  @RequestMapping(value = "/canviarPipella/{pipella}", method = RequestMethod.GET)
  public ModelAndView canviarPipella(HttpServletRequest request, HttpServletResponse response,
      @PathVariable String pipella) throws Exception {

    if (pipella != null && pipella.trim().length() != 0) {

      // TODO GENAPP Afegir altres pipelles !!!!!
      /*
      if ("ROLE_ADEN".equals(pipella)) {
        //return new ModelAndView("role_aden");
        return new ModelAndView(new RedirectView("/aden/peticionscaducades/list/1", true));
      }
      */
      
      if ("admin".equals(pipella)) {
        return new ModelAndView(new RedirectView("/admin/option1", true));
      }
      
      if ("user".equals(pipella)) {
        return new ModelAndView(new RedirectView("/user/option1", true));
      }
      
      if ("webdb".equals(pipella)) {
        return new ModelAndView("webdb");
      }
      
      if (Configuracio.isDesenvolupament() && "desenvolupament".equals(pipella)) {
        return new ModelAndView("desenvolupament");
      }

      log.error("S'ha accedit a canviarPipella amb un paràmetre desconegut: " + pipella);
    }

    return new ModelAndView("principal");
  }
  
  

}
