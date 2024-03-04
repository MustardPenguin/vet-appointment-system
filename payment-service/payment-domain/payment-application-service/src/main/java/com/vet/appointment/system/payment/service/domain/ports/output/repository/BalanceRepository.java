package com.vet.appointment.system.payment.service.domain.ports.output.repository;

import com.vet.appointment.system.payment.service.domain.dto.message.AccountModel;
import com.vet.appointment.system.payment.service.domain.entity.Balance;

import java.util.Optional;
import java.util.UUID;

public interface BalanceRepository {

    void createNewAccountBalance(AccountModel accountModel);

    Optional<Balance> findBalanceByAccountId(UUID accountId);
}
