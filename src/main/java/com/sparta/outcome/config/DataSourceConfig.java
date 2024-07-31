package com.sparta.outcome.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Autowired
    private Environment env;
//    @Bean
//    @Primary
//    @ConfigurationProperties("spring.datasource.write")
//    public DataSourceProperties writeDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    @Primary
//    public DataSource writeDataSource() {
//        return writeDataSourceProperties()
//                .initializeDataSourceBuilder()
//                .type(HikariDataSource.class)
//                .build();
//    }
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.read")
//    public DataSourceProperties readDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    public DataSource readDataSource() {
//        return readDataSourceProperties()
//                .initializeDataSourceBuilder()
//                .type(HikariDataSource.class)
//                .build();
//    }

    @Bean(name = "writeDataSource")
    @Primary
    public DataSource writeDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(env.getProperty("spring.datasource.write.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.write.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.write.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.write.driver-class-name"));
        dataSource.setMaximumPoolSize(env.getProperty("spring.datasource.write.hikari.maximum-pool-size", Integer.class));
        dataSource.setMinimumIdle(env.getProperty("spring.datasource.batch.write.minimum-idle", Integer.class));
        dataSource.setIdleTimeout(env.getProperty("spring.datasource.batch.write.idle-timeout", Long.class));
        dataSource.setConnectionTimeout(env.getProperty("spring.datasource.write.hikari.connection-timeout", Long.class));
        dataSource.setMaxLifetime(env.getProperty("spring.datasource.write.hikari.max-lifetime", Long.class));
        dataSource.setKeepaliveTime(env.getProperty("spring.datasource.write.hikari.keepalive-time", Long.class));
        dataSource.setValidationTimeout(env.getProperty("spring.datasource.write.hikari.validation-timeout", Long.class));
        return dataSource;
    }

    @Bean(name = "readDataSource")
    public DataSource readDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(env.getProperty("spring.datasource.read.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.read.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.read.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.read.driver-class-name"));
        dataSource.setMaximumPoolSize(env.getProperty("spring.datasource.read.hikari.maximum-pool-size", Integer.class));
        dataSource.setMinimumIdle(env.getProperty("spring.datasource.read.hikari.minimum-idle", Integer.class));
        dataSource.setIdleTimeout(env.getProperty("spring.datasource.read.hikari.idle-timeout", Long.class));
        dataSource.setConnectionTimeout(env.getProperty("spring.datasource.read.hikari.connection-timeout", Long.class));
        dataSource.setMaxLifetime(env.getProperty("spring.datasource.read.hikari.max-lifetime", Long.class));
        dataSource.setKeepaliveTime(env.getProperty("spring.datasource.read.hikari.keepalive-time", Long.class));
        dataSource.setValidationTimeout(env.getProperty("spring.datasource.read.hikari.validation-timeout", Long.class));
        return dataSource;
    }


}