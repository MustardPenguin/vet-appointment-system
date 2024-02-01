package com.vet.appointment.system.appointment.service.domain.ports.input.message.listener;

import com.vet.appointment.system.appointment.service.domain.dto.message.AccountModel;

public interface AccountCreatedMessageListener {

    void accountCreated(AccountModel accountModel);
}
