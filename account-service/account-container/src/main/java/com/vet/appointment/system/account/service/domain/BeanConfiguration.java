package com.vet.appointment.system.account.service.domain;


import com.vet.appointment.service.account.service.domain.AccountDomainService;
import com.vet.appointment.service.account.service.domain.impl.AccountDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public AccountDomainService accountDomainService() {
        return new AccountDomainServiceImpl();
    }
}
