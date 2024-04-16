package com.vet.appointment.system.payment.service.domain.impl;

import com.vet.appointment.system.payment.service.domain.dto.model.TransactionModel;
import com.vet.appointment.system.payment.service.domain.dto.response.BalanceResponseBody;
import com.vet.appointment.system.payment.service.domain.dto.response.TransactionsResponseBody;
import com.vet.appointment.system.payment.service.domain.entity.Balance;
import com.vet.appointment.system.payment.service.domain.ports.input.PaymentApplicationService;
import com.vet.appointment.system.payment.service.domain.ports.output.repository.BalanceRepository;
import com.vet.appointment.system.payment.service.domain.ports.output.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class PaymentApplicationServiceImpl implements PaymentApplicationService {

    private final TransactionRepository transactionRepository;
    private final BalanceRepository balanceRepository;

    public PaymentApplicationServiceImpl(TransactionRepository transactionRepository, BalanceRepository balanceRepository) {
        this.transactionRepository = transactionRepository;
        this.balanceRepository = balanceRepository;
    }

    @Override
    public BalanceResponseBody getBalanceByAccountId(UUID accountId) {
        Optional<Balance> response = balanceRepository.findBalanceByAccountId(accountId);
        if(response.isEmpty()) {
            log.error("Balance not found for account id {}", accountId);
            return new BalanceResponseBody(null, HttpStatusCode.valueOf(404));
        }
        return new BalanceResponseBody(response.get(), HttpStatusCode.valueOf(200));
    }

    @Override
    public TransactionsResponseBody getTransactionsByAccountId(UUID accountId) {
        List<TransactionModel> transactions = transactionRepository.findTransactionsByAccountId(accountId);
        return new TransactionsResponseBody(transactions, HttpStatusCode.valueOf(200));
    }
}
