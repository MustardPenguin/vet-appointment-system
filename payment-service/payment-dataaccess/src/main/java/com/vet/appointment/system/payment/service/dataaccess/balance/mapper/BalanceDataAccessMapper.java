package com.vet.appointment.system.payment.service.dataaccess.balance.mapper;

import com.vet.appointment.system.payment.service.dataaccess.balance.entity.BalanceEntity;
import com.vet.appointment.system.payment.service.domain.entity.Balance;
import org.springframework.stereotype.Component;

@Component
public class BalanceDataAccessMapper {

    public Balance balanceEntityToBalance(BalanceEntity balanceEntity) {
        return Balance.builder()
                .id(balanceEntity.getId())
                .accountId(balanceEntity.getAccountId())
                .email(balanceEntity.getEmail())
                .credit(balanceEntity.getCredit())
                .version(balanceEntity.getVersion())
                .build();
    }

    public BalanceEntity balanceToBalanceEntity(Balance balance) {
        return BalanceEntity.builder()
                .id(balance.getId())
                .accountId(balance.getAccountId())
                .email(balance.getEmail())
                .credit(balance.getCredit())
                .version(balance.getVersion())
                .build();
    }
}
