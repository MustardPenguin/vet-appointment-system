package com.vet.appointment.system.payment.service.dataaccess.balance.mapper;

import com.vet.appointment.system.payment.service.dataaccess.balance.entity.BalanceEntity;
import com.vet.appointment.system.payment.service.domain.entity.Balance;
import org.springframework.stereotype.Component;

@Component
public class BalanceDataAccessMapper {

    public Balance balanceEntityToBalance(BalanceEntity balanceEntity) {
        return new Balance(
                balanceEntity.getId(),
                balanceEntity.getAccountId(),
                balanceEntity.getEmail(),
                balanceEntity.getCredit());
    }

    public BalanceEntity balanceToBalanceEntity(Balance balance) {
        return new BalanceEntity(
                balance.getId(),
                balance.getAccountId(),
                balance.getEmail(),
                balance.getCredit());
    }
}
