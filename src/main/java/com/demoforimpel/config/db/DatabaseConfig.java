package com.demoforimpel.config.db;

import com.demoforimpel.data.props.ApplicationProperties;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Properties;

@Configuration
public class DatabaseConfig {

    private final ApplicationProperties applicationProperties;

    public DatabaseConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public DriverManagerDataSource driverManagerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(applicationProperties.getDatabase().getUrl());
        dataSource.setDriverClassName(applicationProperties.getDatabase().getDriver());
        dataSource.setUsername(applicationProperties.getDatabase().getUsername());
        dataSource.setPassword(applicationProperties.getDatabase().getPassword());
        Properties properties = new Properties();
        properties.setProperty(Environment.USE_SQL_COMMENTS,"false");
        dataSource.setConnectionProperties(properties);
        return dataSource;
    }

}
