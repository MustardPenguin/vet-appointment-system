package com.vet.appointment.system.payment.service.domain.ports.output.repository;

import com.vet.appointment.system.payment.service.domain.dto.model.TransactionModel;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository {

    TransactionModel save(TransactionModel transactionModel);

    TransactionModel findById(UUID id);

    List<TransactionModel> findTransactionsByAccountId(UUID accountId);
}
