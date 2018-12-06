package com.example.travello.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DataConfig {

    @Autowired
    Environment env;

    @Bean(name = "flyway")
    public Flyway getFlywayBean(){
        Flyway flyway = Flyway.configure()
                .dataSource(
                        env.getProperty("spring.datasource.url"),
                        env.getProperty("spring.datasource.username"),
                        env.getProperty("spring.datasource.password")
                )
                .schemas(env.getProperty("application.db.schema"))
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .load();
        flyway.migrate();
        return flyway;
    }
}
