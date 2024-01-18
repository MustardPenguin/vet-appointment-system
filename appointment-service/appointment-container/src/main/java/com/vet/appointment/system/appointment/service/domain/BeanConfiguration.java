package com.vet.appointment.system.appointment.service.domain;

import com.vet.appointment.system.appointment.service.domain.impl.AppointmentDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public AppointmentDomainService appointmentDomainService() {
        return new AppointmentDomainServiceImpl();
    }
}
