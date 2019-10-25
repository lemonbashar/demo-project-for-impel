package com.demoforimpel.data.props;

public class ApplicationProperties {
    private Database database = new Database();
    private JWT jwt=new JWT();

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public JWT getJwt() {
        return jwt;
    }

    public void setJwt(JWT jwt) {
        this.jwt = jwt;
    }
}
