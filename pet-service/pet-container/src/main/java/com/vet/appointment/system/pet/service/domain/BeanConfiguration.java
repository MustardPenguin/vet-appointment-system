package com.vet.appointment.system.pet.service.domain;

import com.vet.appointment.system.pet.service.domain.PetDomainService;
import com.vet.appointment.system.pet.service.domain.impl.PetDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PetDomainService petDomainService() {
        return new PetDomainServiceImpl();
    }
}
