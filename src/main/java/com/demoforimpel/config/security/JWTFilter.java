package com.demoforimpel.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JWTFilter extends GenericFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";

    @Autowired
    protected TokenProvider tokenProvider;


    public static String findHeaderValue(HttpServletRequest httpServletRequest, String headerName) {
        return httpServletRequest.getHeader(headerName);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwt = resolveToken(httpServletRequest);
        resolveJWT(request, response, chain, jwt);
    }

    protected void resolveJWT(ServletRequest request, ServletResponse response, FilterChain chain, String jwt) throws IOException, ServletException {
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    public static String resolveToken(HttpServletRequest httpServletRequest) {
        String bearer = findHeaderValue(httpServletRequest, AUTHORIZATION_HEADER);
        return resolveBearer(bearer);
    }

    public static String resolveBearer(String headerWithBearer) {
        if (StringUtils.hasText(headerWithBearer) && headerWithBearer.startsWith(BEARER)) {
            return headerWithBearer.substring(BEARER.length());
        }
        return null;
    }
}
