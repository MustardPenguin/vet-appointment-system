package com.vet.appointment.system.payment.service.domain.ports.input;

import com.vet.appointment.system.payment.service.domain.dto.response.BalanceResponseBody;
import com.vet.appointment.system.payment.service.domain.dto.response.TransactionsResponseBody;

import java.util.UUID;

public interface PaymentApplicationService {

    BalanceResponseBody getBalanceByAccountId(UUID accountId);

    TransactionsResponseBody getTransactionsByAccountId(UUID accountId);
}
