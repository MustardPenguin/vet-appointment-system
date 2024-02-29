package com.vet.appointment.system.payment.service.dataaccess.balance.adapter;

import com.vet.appointment.system.payment.service.dataaccess.balance.entity.BalanceEntity;
import com.vet.appointment.system.payment.service.dataaccess.balance.repository.BalanceJpaRepository;
import com.vet.appointment.system.payment.service.domain.dto.message.AccountModel;
import com.vet.appointment.system.payment.service.domain.ports.output.repository.BalanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Component
public class BalanceRepositoryImpl implements BalanceRepository {

    private final BalanceJpaRepository balanceJpaRepository;

    public BalanceRepositoryImpl(BalanceJpaRepository balanceJpaRepository) {
        this.balanceJpaRepository = balanceJpaRepository;
    }

    @Override
    public void createNewAccountBalance(AccountModel accountModel) {
        BalanceEntity balanceEntity = new BalanceEntity(
                UUID.randomUUID(), accountModel.getId(), accountModel.getEmail(), new BigDecimal(50.00));
        BalanceEntity response = balanceJpaRepository.save(balanceEntity);
        if(response == null) {
            throw new RuntimeException("Balance could not be created for account id: " + accountModel.getId());
        }
        log.info("Successfully created balance for account id: {}", accountModel.getId());
    }
}
