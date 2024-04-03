package com.vet.appointment.system.account.service.messaging.mapper;

import com.vet.appointment.service.account.service.domain.entity.Account;
import com.vet.appointment.system.messaging.event.AccountCreatedEventPayload;
import org.springframework.stereotype.Component;

@Component
public class AccountMessagingDataMapper {

    public Account accountCreatedEventPayloadToAccount(AccountCreatedEventPayload accountCreatedEventPayload) {
        return Account.builder()
                .id(accountCreatedEventPayload.getId())
                .email(accountCreatedEventPayload.getEmail())
                .firstName(accountCreatedEventPayload.getFirstName())
                .lastName(accountCreatedEventPayload.getLastName())
                .password(accountCreatedEventPayload.getPassword())
                .build();
    }
}
