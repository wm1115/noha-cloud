package com.noha.datasource.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(
        prefix = "spring.datasource"
)
@Configuration
public class DataSourceProperties {
}
