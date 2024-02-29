package com.vet.appointment.system.payment.service.domain.impl;

import com.vet.appointment.system.payment.service.domain.dto.message.AccountModel;
import com.vet.appointment.system.payment.service.domain.ports.input.message.listener.AccountCreatedMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountCreatedMessageListenerImpl implements AccountCreatedMessageListener {

    @Override
    public void accountCreated(AccountModel accountModel) {
        log.info("Account id: {} received", accountModel.getId());


    }
}
