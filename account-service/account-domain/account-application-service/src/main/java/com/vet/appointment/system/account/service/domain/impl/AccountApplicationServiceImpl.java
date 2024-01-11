package com.vet.appointment.system.account.service.domain.impl;

import com.vet.appointment.service.account.service.domain.event.AccountCreatedEvent;
import com.vet.appointment.system.account.service.domain.CreateAccountCommandHandler;
import com.vet.appointment.system.account.service.domain.dto.create.CreateAccountCommand;
import com.vet.appointment.system.account.service.domain.mapper.AccountDataMapper;
import com.vet.appointment.system.account.service.domain.ports.input.AccountApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountApplicationServiceImpl implements AccountApplicationService {


    private final AccountDataMapper accountDataMapper;
    private final CreateAccountCommandHandler createAccountCommandHandler;

    public AccountApplicationServiceImpl(AccountDataMapper accountDataMapper,
                                         CreateAccountCommandHandler createAccountCommandHandler) {
        this.accountDataMapper = accountDataMapper;
        this.createAccountCommandHandler = createAccountCommandHandler;
    }

    @Override
    public void createAccount(CreateAccountCommand createAccountCommand) {
        log.info("Creating account at service layer");
        AccountCreatedEvent accountCreatedEvent = createAccountCommandHandler.createAccount(createAccountCommand);

    }
}
