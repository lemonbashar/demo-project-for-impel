package com.demoforimpel.config.db;

import com.demoforimpel.data.props.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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
        return dataSource;
    }

}
