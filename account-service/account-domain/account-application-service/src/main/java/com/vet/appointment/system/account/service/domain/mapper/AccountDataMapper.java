package com.vet.appointment.system.account.service.domain.mapper;

import com.vet.appointment.service.account.service.domain.entity.Account;
import com.vet.appointment.system.account.service.domain.dto.create.CreateAccountCommand;
import org.springframework.stereotype.Component;

@Component
public class AccountDataMapper {

    public Account createAccountCommandToAccount(CreateAccountCommand createAccountCommand) {
        return Account.builder()
                .email(createAccountCommand.getEmail())
                .password(createAccountCommand.getPassword())
                .firstName(createAccountCommand.getFirstName())
                .lastName(createAccountCommand.getLastName())
                .build();
    }
}