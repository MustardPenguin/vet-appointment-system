package com.vet.appointment.system.payment.service.domain.ports.output.repository;

import com.vet.appointment.system.payment.service.domain.dto.message.AccountModel;

public interface BalanceRepository {

    void createNewAccountBalance(AccountModel accountModel);
}
