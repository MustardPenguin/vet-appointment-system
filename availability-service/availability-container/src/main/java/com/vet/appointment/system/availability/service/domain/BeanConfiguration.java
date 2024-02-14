package com.vet.appointment.system.availability.service.domain;

import com.vet.appointment.system.availability.service.domain.impl.AvailabilityDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public AvailabilityDomainService availabilityDomainService() {
        return new AvailabilityDomainServiceImpl();
    }
}
