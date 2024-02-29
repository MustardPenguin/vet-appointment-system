package com.vet.appointment.system.payment.service.domain.ports.input.message.listener;

import com.vet.appointment.system.payment.service.domain.dto.message.AccountModel;

public interface AccountCreatedMessageListener {

    void accountCreated(AccountModel accountModel);
}
