package com.station.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com")
@SpringBootApplication
@EnableJpaRepositories("com.station.persist")
@EntityScan("com.station.domain")
public class StationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StationServiceApplication.class, args);
    }
}
