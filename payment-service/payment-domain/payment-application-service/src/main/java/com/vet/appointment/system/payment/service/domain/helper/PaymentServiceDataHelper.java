package com.vet.appointment.system.payment.service.domain.helper;

import com.vet.appointment.system.payment.service.domain.dto.model.TransactionModel;
import com.vet.appointment.system.payment.service.domain.entity.Balance;
import com.vet.appointment.system.payment.service.domain.exception.PaymentDomainException;
import com.vet.appointment.system.payment.service.domain.ports.output.repository.BalanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class PaymentServiceDataHelper {

    private final BalanceRepository balanceRepository;

    public PaymentServiceDataHelper(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public Balance findBalanceByAccountId(UUID accountId) {
        Optional<Balance> optionalBalance = balanceRepository.findBalanceByAccountId(accountId);
        if(optionalBalance.isEmpty()) {
            log.error("Unable to find balance for account id: {}", accountId);
            throw new PaymentDomainException("Unable to find balance for account id: " + accountId);
        }
        return optionalBalance.get();
    }

    @Transactional
    public void saveBalance(Balance balance) {
        Balance response = balanceRepository.save(balance);
        if(response == null) {
            log.error("Could not persist balance entity of id {} with account id {}", balance.getId(), balance.getAccountId());
            throw new PaymentDomainException("Could not persist balance entity of id " + balance.getId()
                    + " with account id " + balance.getAccountId());
        }
        log.info("Successfully persisted balance entity of id {} with account id {}", balance.getId(), balance.getAccountId());
    }

}
