package com.vet.appointment.system.account.service.domain.ports.input;

import com.vet.appointment.service.account.service.domain.entity.Account;

public interface AccountCreatedMessageListener {

    void accountCreated(Account account);
}
