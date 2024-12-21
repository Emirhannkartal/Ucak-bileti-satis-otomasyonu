package com.samilemir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.samilemir"})
@EntityScan(basePackages = {"com.samilemir"})
@EnableJpaRepositories(basePackages = {"com.samilemir"})
public class UcakbiletisatisApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(UcakbiletisatisApplicationStarter.class, args);
    }
}
