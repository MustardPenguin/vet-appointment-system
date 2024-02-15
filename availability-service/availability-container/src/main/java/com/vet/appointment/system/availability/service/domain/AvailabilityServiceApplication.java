package com.vet.appointment.system.availability.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.vet.appointment.system.availability.service.dataaccess")
@EntityScan(basePackages = "com.vet.appointment.system.availability.service.dataaccess")
@SpringBootApplication(scanBasePackages = "com.vet.appointment.system")
public class AvailabilityServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AvailabilityServiceApplication.class, args);
    }
}
