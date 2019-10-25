package com.demoforimpel.service.impl;

import com.demoforimpel.config.security.TokenProvider;
import com.demoforimpel.data.AccountInfo;
import com.demoforimpel.data.LoginInfo;
import com.demoforimpel.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AccountInfo authenticate(LoginInfo loginInfo) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(loginInfo.getUsername(),loginInfo.getPassword());
            Authentication authentication=authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new AccountInfo(loginInfo.getUsername(),tokenProvider.createToken(authentication,loginInfo), authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new));
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
