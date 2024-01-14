package com.vet.appointment.system.account.service.domain.impl;

import com.vet.appointment.service.account.service.domain.event.AccountCreatedEvent;
import com.vet.appointment.system.account.service.domain.CreateAccountCommandHandler;
import com.vet.appointment.system.account.service.domain.dto.create.CreateAccountCommand;
import com.vet.appointment.system.account.service.domain.dto.create.CreateAccountResponse;
import com.vet.appointment.system.account.service.domain.mapper.AccountDataMapper;
import com.vet.appointment.system.account.service.domain.ports.input.AccountApplicationService;
import com.vet.appointment.system.account.service.domain.ports.output.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountApplicationServiceImpl implements AccountApplicationService {


    private final AccountDataMapper accountDataMapper;
    private final CreateAccountCommandHandler createAccountCommandHandler;
    private final AccountRepository accountRepository;

    public AccountApplicationServiceImpl(AccountDataMapper accountDataMapper,
                                         CreateAccountCommandHandler createAccountCommandHandler, AccountRepository accountRepository) {
        this.accountDataMapper = accountDataMapper;
        this.createAccountCommandHandler = createAccountCommandHandler;
        this.accountRepository = accountRepository;
    }

    @Override
    public CreateAccountResponse createAccount(CreateAccountCommand createAccountCommand) {
        if(accountRepository.isEmailTaken(createAccountCommand.getEmail())) {
            return new CreateAccountResponse("Email " + createAccountCommand.getEmail() + " is already taken!", 400);
        }

        AccountCreatedEvent accountCreatedEvent = createAccountCommandHandler.createAccountFromCommand(createAccountCommand);



        return new CreateAccountResponse("Successfully created account!", 201);
    }
}
