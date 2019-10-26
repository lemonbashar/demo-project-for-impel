package com.demoforimpel.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JWTFilter extends GenericFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";

    private final TokenProvider tokenProvider;

    public JWTFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    private static String resolveBearer(String headerWithBearer) {
        return StringUtils.hasText(headerWithBearer) && headerWithBearer.startsWith(BEARER)? headerWithBearer.substring(BEARER.length()) : null ;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwt = resolveToken(httpServletRequest);
        resolveJWT(request, response, chain, jwt);
    }

    private String findHeaderValue(HttpServletRequest httpServletRequest, String headerName) {
        return httpServletRequest.getHeader(headerName);
    }

    private void resolveJWT(ServletRequest request, ServletResponse response, FilterChain chain, String jwt) throws IOException, ServletException {
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest httpServletRequest) {
        String bearer = findHeaderValue(httpServletRequest, AUTHORIZATION_HEADER);
        return resolveBearer(bearer);
    }
}
