package com.demoforimpel.config.props;

public class ApplicationProperties {
    private Database database = new Database();

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
