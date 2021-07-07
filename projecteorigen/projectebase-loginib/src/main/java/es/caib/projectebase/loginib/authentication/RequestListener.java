package es.caib.projectebase.loginib.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class RequestListener implements ServletRequestListener {

    private static final Logger LOG = LoggerFactory.getLogger(RequestListener.class);

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        LOG.info("{} {}", request.getMethod(), request.getRequestURI());
    }
}
