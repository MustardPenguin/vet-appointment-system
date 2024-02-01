package com.vet.appointment.system.appointment.service.domain.impl;

import com.vet.appointment.system.appointment.service.domain.dto.message.AccountModel;
import com.vet.appointment.system.appointment.service.domain.exception.AppointmentDomainException;
import com.vet.appointment.system.appointment.service.domain.ports.input.message.listener.AccountCreatedMessageListener;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.AccountRepository;
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
    public void accountCreated(AccountModel accountModel) {
        AccountModel response = accountRepository.save(accountModel);
        if(response == null) {
            log.error("Could not save account with id: {} for event payload!", response.getId());
            throw new AppointmentDomainException("Could not save account with id: " + response.getId()
                    + " for event payload!");
        }
        log.info("Successfully saved account with id: {} for event payload!", response.getId());
    }
}
