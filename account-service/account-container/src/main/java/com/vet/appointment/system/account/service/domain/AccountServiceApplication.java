package com.vet.appointment.system.account.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.vet.appointment.system.account.service.dataaccess"})
@EntityScan(basePackages = {"com.vet.appointment.system.account.service.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.vet.appointment.system")
public class AccountServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
}
