package com.demoforimpel.data.props;

public class Database {
    private String url;
    private String username;
    private String password;
    private String driver;
    private String hbm2DDLAuto = "update";
    private String dialect;
    private boolean formatSQL = true;
    private boolean showSql = true;
    private Jpa jpa = new Jpa();
    private DatabaseType databaseType;
    private boolean generateDDL=true;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Jpa getJpa() {
        return jpa;
    }

    public void setJpa(Jpa jpa) {
        this.jpa = jpa;
    }

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }

    public String getHbm2DDLAuto() {
        return hbm2DDLAuto;
    }

    public void setHbm2DDLAuto(String hbm2DDLAuto) {
        this.hbm2DDLAuto = hbm2DDLAuto;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public boolean isFormatSQL() {
        return formatSQL;
    }

    public void setFormatSQL(boolean formatSQL) {
        this.formatSQL = formatSQL;
    }

    public boolean isShowSql() {
        return showSql;
    }

    public void setShowSql(boolean showSql) {
        this.showSql = showSql;
    }

    public boolean isGenerateDDL() {
        return generateDDL;
    }

    public void setGenerateDDL(boolean generateDDL) {
        this.generateDDL = generateDDL;
    }
}
