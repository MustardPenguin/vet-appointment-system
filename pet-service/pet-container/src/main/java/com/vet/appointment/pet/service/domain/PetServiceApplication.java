package com.vet.appointment.pet.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.vet.appointment.system")
public class PetServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PetServiceApplication.class, args);
    }
}
