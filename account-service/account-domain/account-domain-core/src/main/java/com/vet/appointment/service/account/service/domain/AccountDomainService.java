package com.vet.appointment.service.account.service.domain;

import com.vet.appointment.service.account.service.domain.entity.Account;
import com.vet.appointment.service.account.service.domain.event.AccountCreatedEvent;

import java.time.ZonedDateTime;

public interface AccountDomainService {
    AccountCreatedEvent validateAndInitiateAccount(Account account);
}
