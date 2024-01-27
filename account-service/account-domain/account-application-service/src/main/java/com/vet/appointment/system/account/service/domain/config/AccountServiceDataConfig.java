package com.vet.appointment.system.account.service.domain.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

public class AccountServiceDataConfig {
    private AccountServiceDataConfig() {}

    public final static String AccountCreatedEventTopicName = "account-created-event";
}
