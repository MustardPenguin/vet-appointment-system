package com.vet.appointment.service.account.service.domain.event;

import com.vet.appointment.service.account.service.domain.entity.Account;
import com.vet.appointment.system.domain.event.DomainEvent;

import java.time.ZonedDateTime;

public class AccountCreatedEvent extends DomainEvent<Account> {

    public AccountCreatedEvent(Account account, ZonedDateTime createdAt) {
        super(account, createdAt);
    }
}
