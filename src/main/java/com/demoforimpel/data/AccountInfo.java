package com.demoforimpel.data;

import java.io.Serializable;

public class AccountInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private boolean authenticated;
    private String[] authorities;
    private String token;

    public AccountInfo() {
    }

    public AccountInfo(String username, String token, String[] authorities) {
        this.username = username;
        this.token=token;
        this.authorities = authorities;
        this.authenticated=true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
