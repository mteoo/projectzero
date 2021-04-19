package com.matheus.dias.projectzero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ProjectZeroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectZeroApplication.class, args);
    }

}
