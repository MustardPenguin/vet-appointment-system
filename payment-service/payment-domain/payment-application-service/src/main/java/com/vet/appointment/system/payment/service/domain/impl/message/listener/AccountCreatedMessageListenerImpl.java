package com.vet.appointment.system.payment.service.domain.impl.message.listener;

import com.vet.appointment.system.payment.service.domain.dto.message.AccountModel;
import com.vet.appointment.system.payment.service.domain.ports.input.message.listener.AccountCreatedMessageListener;
import com.vet.appointment.system.payment.service.domain.ports.output.repository.BalanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountCreatedMessageListenerImpl implements AccountCreatedMessageListener {

    private final BalanceRepository balanceRepository;

    public AccountCreatedMessageListenerImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public void accountCreated(AccountModel accountModel) {
        log.info("Creating balance for account id {}", accountModel.getId());
        balanceRepository.createNewAccountBalance(accountModel);
    }
}
