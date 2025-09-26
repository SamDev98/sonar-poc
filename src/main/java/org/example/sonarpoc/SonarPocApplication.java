package org.example.sonarpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class SonarPocApplication {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(SonarPocApplication.class.getName());

        logger.info("Starting Sonar POC app...");
        SpringApplication.run(SonarPocApplication.class, args);
    }
}
