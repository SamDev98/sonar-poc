package org.example.sonarpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SonarPocApplication {
    public static void main(String[] args) {
        // Sonar: usar logger ao invés de System.out
        System.out.println("Starting Sonar POC app...");
        SpringApplication.run(SonarPocApplication.class, args);
    }
}
