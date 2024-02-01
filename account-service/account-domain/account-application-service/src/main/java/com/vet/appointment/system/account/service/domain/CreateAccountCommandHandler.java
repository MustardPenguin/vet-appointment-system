package com.vet.appointment.system.account.service.domain;

import com.vet.appointment.service.account.service.domain.AccountDomainService;
import com.vet.appointment.service.account.service.domain.entity.Account;
import com.vet.appointment.service.account.service.domain.event.AccountCreatedEvent;
import com.vet.appointment.service.account.service.domain.exception.AccountDomainException;
import com.vet.appointment.system.account.service.domain.dto.create.CreateAccountCommand;
import com.vet.appointment.system.account.service.domain.mapper.AccountDataMapper;
import com.vet.appointment.system.account.service.domain.ports.output.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateAccountCommandHandler {

    private final AccountDomainService accountDomainService;
    private final AccountDataMapper accountDataMapper;
    private final AccountRepository accountRepository;


    public CreateAccountCommandHandler(AccountDomainService accountDomainService,
                                       AccountDataMapper accountDataMapper,
                                       AccountRepository accountRepository) {
        this.accountDomainService = accountDomainService;
        this.accountDataMapper = accountDataMapper;
        this.accountRepository = accountRepository;
    }

    public AccountCreatedEvent createAccountFromCommand(CreateAccountCommand createAccountCommand) {
        Account account = accountDataMapper.createAccountCommandToAccount(createAccountCommand);
        AccountCreatedEvent accountCreatedEvent = accountDomainService.validateAndInitiateAccount(account);

        Account savedAccount = accountRepository.registerAccount(account);
        if(savedAccount == null) {
            log.error("Could not save account!");
            throw new AccountDomainException("Could not save account!");
        }
        log.info("Successfully saved account with email: {} and id: {}",
                savedAccount.getEmail(), savedAccount.getId().getValue().toString());
        return accountCreatedEvent;
    }
}
