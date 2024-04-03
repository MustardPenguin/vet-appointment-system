package com.vet.appointment.system.account.service.domain.impl;

import com.vet.appointment.service.account.service.domain.entity.Account;
import com.vet.appointment.service.account.service.domain.exception.AccountDomainException;
import com.vet.appointment.system.account.service.domain.ports.input.AccountCreatedMessageListener;
import com.vet.appointment.system.account.service.domain.ports.output.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountCreatedMessageListenerImpl implements AccountCreatedMessageListener {

    private final AccountRepository accountRepository;

    public AccountCreatedMessageListenerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void accountCreated(Account account) {
        if(accountRepository.isEmailTaken(account.getEmail())) {
            log.error("Already received propagation event for email of {}, or this instance is the source of the event!", account.getEmail());
            return;
        }
        Account response = accountRepository.registerAccount(account);
        if(response == null) {
            log.error("Failed to register account of email {} and id {}!", account.getEmail(), account.getId());
            throw new AccountDomainException("Failed to register account of email " + account.getEmail() + " and id " + account.getId() + "!");
        }
        log.info("Successfully saved account of email {} and id {} from propagation event!", account.getEmail(), account.getId());
    }
}
