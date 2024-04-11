package com.vet.appointment.system.payment.service.dataaccess.balance.adapter;

import com.vet.appointment.system.payment.service.dataaccess.balance.entity.BalanceEntity;
import com.vet.appointment.system.payment.service.dataaccess.balance.mapper.BalanceDataAccessMapper;
import com.vet.appointment.system.payment.service.dataaccess.balance.repository.BalanceJpaRepository;
import com.vet.appointment.system.payment.service.domain.dto.message.AccountModel;
import com.vet.appointment.system.payment.service.domain.entity.Balance;
import com.vet.appointment.system.payment.service.domain.ports.output.repository.BalanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class BalanceRepositoryImpl implements BalanceRepository {

    private final BalanceDataAccessMapper balanceDataAccessMapper;
    private final BalanceJpaRepository balanceJpaRepository;

    public BalanceRepositoryImpl(BalanceDataAccessMapper balanceDataAccessMapper,
                                 BalanceJpaRepository balanceJpaRepository) {
        this.balanceDataAccessMapper = balanceDataAccessMapper;
        this.balanceJpaRepository = balanceJpaRepository;
    }

    @Override
    public void createNewAccountBalance(AccountModel accountModel) {
        BalanceEntity balanceEntity = BalanceEntity.builder()
                .id(UUID.randomUUID())
                .accountId(accountModel.getId())
                .email(accountModel.getEmail())
                .credit(new BigDecimal(50.00))
                .build();
        BalanceEntity response = balanceJpaRepository.save(balanceEntity);
        if(response == null) {
            throw new RuntimeException("Balance could not be created for account id: " + accountModel.getId());
        }
        log.info("Successfully created balance for account id: {}", accountModel.getId());
    }

    @Override
    public Optional<Balance> findBalanceByAccountId(UUID accountId) {
        return balanceJpaRepository.findByAccountId(accountId)
                .map(balanceDataAccessMapper::balanceEntityToBalance);
    }

    @Override
    public Balance save(Balance balance) {
        try {
            BalanceEntity balanceEntity = balanceJpaRepository.save(balanceDataAccessMapper.balanceToBalanceEntity(balance));
            return balanceDataAccessMapper.balanceEntityToBalance(balanceEntity);
        } catch (Exception e) {
            log.error("Unable to save balance entity, error: {}", e.getMessage());
            return null;
        }

    }
}
