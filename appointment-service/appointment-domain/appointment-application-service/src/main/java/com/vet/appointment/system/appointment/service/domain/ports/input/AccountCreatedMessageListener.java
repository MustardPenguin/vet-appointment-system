package com.vet.appointment.system.appointment.service.domain.ports.input;

import com.vet.appointment.system.appointment.service.domain.dto.message.AccountModel;

public interface AccountCreatedMessageListener {

    void accountCreated(AccountModel accountModel);
}
