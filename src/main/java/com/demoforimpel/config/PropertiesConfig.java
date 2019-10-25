package com.demoforimpel.config;

import com.demoforimpel.config.props.ApplicationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class PropertiesConfig {

    @Bean("applicationProperties")
    @ConfigurationProperties(prefix = "application")
    public ApplicationProperties applicationProperties() {
        return new ApplicationProperties();
    }
}
