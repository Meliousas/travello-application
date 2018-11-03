//package com.example.travello.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.jdbc.datasource.SimpleDriverDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataConfig {
//
//    @Autowired
//    Environment env;
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("myapp.dataSource.driver"));
//        dataSource.setUrl(env.getProperty("myapp.dataSource.url"));
//        dataSource.setUsername(env.getProperty("myapp.dataSource.username"));
//        dataSource.setPassword(env.getProperty("myapp.dataSource.password"));
//        return dataSource;
//    }
//
//}
