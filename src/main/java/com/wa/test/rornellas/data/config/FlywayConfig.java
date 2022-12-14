package com.wa.test.rornellas.data.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    @Value("${spring.flyway.baseline-on-migrate:true}")
    Boolean flywayBaselineOnMigrate;
    @Value("${spring.flyway.url}")
    String flywayUrl;
    @Value("${spring.flyway.user:sa}")
    String flywayUser;
    @Value("${spring.flyway.password}")
    String flywayPassword;

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        return new Flyway(Flyway.configure()
                .baselineOnMigrate(this.flywayBaselineOnMigrate)
                .dataSource(
                    this.flywayUrl,
                    this.flywayUser,
                    this.flywayPassword
                )
        );
    }
}