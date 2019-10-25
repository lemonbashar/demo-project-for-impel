package com.demoforimpel.data;

import java.io.Serializable;
import java.math.BigInteger;

public class LoginInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String username;
    private String password;
    private boolean rememberMe;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}

