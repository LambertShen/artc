package org.artc.security.jwt;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.artc.security.constants.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtFilter extends BasicHttpAuthenticationFilter {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (!isLoginAttempt(request, response)) {
            return true;
        }
        try {
            executeLogin(request, response);
            return true;
        } catch (Exception e) {
            throw new AuthenticationException("Token失效, 请重新登陆!");
        }
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        if (antPathMatcher.match("/security/login", req.getRequestURI())) {
            return true;
        }
        String token = req.getHeader(Constants.ACCESS_TOKEN);
        return token != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(Constants.ACCESS_TOKEN);
        JwtToken jwtToken = new JwtToken(token);
        getSubject(request, response).login(jwtToken);
        return true;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Access-control-Allow-Origin", req.getHeader("Origin"));
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        resp.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));
        if(req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            resp.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
