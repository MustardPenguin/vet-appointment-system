package com.vet.appointment.system.availability.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.vet.appointment.system")
public class AvailabilityServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AvailabilityServiceApplication.class, args);
    }
}
