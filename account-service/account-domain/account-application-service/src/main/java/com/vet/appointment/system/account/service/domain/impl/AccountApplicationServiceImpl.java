package com.vet.appointment.system.account.service.domain.impl;

import com.vet.appointment.service.account.service.domain.event.AccountCreatedEvent;
import com.vet.appointment.system.account.service.domain.outbox.scheduler.appointment.AppointmentOutboxHelper;
import com.vet.appointment.system.account.service.domain.CreateAccountCommandHandler;
import com.vet.appointment.system.account.service.domain.dto.create.CreateAccountCommand;
import com.vet.appointment.system.account.service.domain.dto.create.CreateAccountResponse;
import com.vet.appointment.system.account.service.domain.mapper.AccountDataMapper;
import com.vet.appointment.system.account.service.domain.ports.input.AccountApplicationService;
import com.vet.appointment.system.account.service.domain.ports.output.repository.AccountRepository;
import com.vet.appointment.system.outbox.OutboxStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountApplicationServiceImpl implements AccountApplicationService {


    private final AccountDataMapper accountDataMapper;
    private final CreateAccountCommandHandler createAccountCommandHandler;
    private final AccountRepository accountRepository;
    private final AppointmentOutboxHelper appointmentOutboxHelper;

    public AccountApplicationServiceImpl(AccountDataMapper accountDataMapper,
                                         CreateAccountCommandHandler createAccountCommandHandler,
                                         AccountRepository accountRepository,
                                         AppointmentOutboxHelper appointmentOutboxHelper) {
        this.accountDataMapper = accountDataMapper;
        this.createAccountCommandHandler = createAccountCommandHandler;
        this.accountRepository = accountRepository;
        this.appointmentOutboxHelper = appointmentOutboxHelper;
    }

    @Override
    public CreateAccountResponse createAccount(CreateAccountCommand createAccountCommand) {
        if(accountRepository.isEmailTaken(createAccountCommand.getEmail())) {
            return new CreateAccountResponse("Email " + createAccountCommand.getEmail() + " is already taken!", 400);
        }

        CreateAccountResponse createAccountResponse = createAccountCommandHandler.createAccountFromCommand(createAccountCommand);

        return createAccountResponse;
    }
}
