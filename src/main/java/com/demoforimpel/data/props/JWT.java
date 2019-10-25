package com.demoforimpel.data.props;

public class JWT {
    private String secret="TUFLSEpJRkhOQ0NEVUdWVVlFQkRDQ0JEVUNHVklERE9GQ0JEQ1VJ";
    private String base64Secret="TUFLSEpJRkhOQ0NEVUdWVVlFQkRDQ0JEVUNHVklERE9GQ0JEQ1VJNDU2Nzg4NzVKSEdVWVY=";
    private int tokenValidityInSeconds=30;
    private int tokenValidityInSecondsForRememberMe=3600;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getBase64Secret() {
        return base64Secret;
    }

    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }

    public int getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }

    public void setTokenValidityInSeconds(int tokenValidityInSeconds) {
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }

    public int getTokenValidityInSecondsForRememberMe() {
        return tokenValidityInSecondsForRememberMe;
    }

    public void setTokenValidityInSecondsForRememberMe(int tokenValidityInSecondsForRememberMe) {
        this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
    }
}
