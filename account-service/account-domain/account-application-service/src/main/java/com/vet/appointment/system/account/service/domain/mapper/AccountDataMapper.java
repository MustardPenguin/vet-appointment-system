package com.vet.appointment.system.account.service.domain.mapper;

import com.vet.appointment.service.account.service.domain.entity.Account;
import com.vet.appointment.service.account.service.domain.event.AccountCreatedEvent;
import com.vet.appointment.system.account.service.domain.dto.create.CreateAccountCommand;
import com.vet.appointment.system.messaging.event.AccountCreatedEventPayload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountDataMapper {

    public Account createAccountCommandToAccount(CreateAccountCommand createAccountCommand) {
        return Account.builder()
                .id(UUID.randomUUID())
                .email(createAccountCommand.getEmail())
                .password(createAccountCommand.getPassword())
                .firstName(createAccountCommand.getFirstName())
                .lastName(createAccountCommand.getLastName())
                .build();
    }

    public AccountCreatedEventPayload accountCreatedEventToAccountAppointmentEventPayload(AccountCreatedEvent accountCreatedEvent) {
        return AccountCreatedEventPayload.builder()
                .id(accountCreatedEvent.getEntity().getId().getValue())
                .createdAt(accountCreatedEvent.getCreatedAt())
                .email(accountCreatedEvent.getEntity().getEmail())
                .firstName(accountCreatedEvent.getEntity().getFirstName())
                .lastName(accountCreatedEvent.getEntity().getLastName())
                .build();
    }
}
