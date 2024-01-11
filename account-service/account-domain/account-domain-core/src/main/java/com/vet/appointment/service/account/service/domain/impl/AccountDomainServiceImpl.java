package com.vet.appointment.service.account.service.domain.impl;

import com.vet.appointment.service.account.service.domain.AccountDomainService;
import com.vet.appointment.service.account.service.domain.entity.Account;
import com.vet.appointment.service.account.service.domain.event.AccountCreatedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.vet.appointment.system.domain.DomainConstants.UTC;

public class AccountDomainServiceImpl implements AccountDomainService {
    @Override
    public AccountCreatedEvent validateAndInitiateAccount(Account account) {

        return new AccountCreatedEvent(account, ZonedDateTime.now(ZoneId.of(UTC)));
    }
}
