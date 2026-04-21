package com.toolsjavachallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.toolsjavachallenge")
@EntityScan(basePackages = "com.toolsjavachallenge.entity")
@EnableJpaRepositories(basePackages = "com.toolsjavachallenge.repository")
public class ToolsjavachallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolsjavachallengeApplication.class, args);
    }

}
