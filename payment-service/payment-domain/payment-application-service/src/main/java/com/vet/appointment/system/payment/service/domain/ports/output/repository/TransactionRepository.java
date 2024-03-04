package com.vet.appointment.system.payment.service.domain.ports.output.repository;

import com.vet.appointment.system.payment.service.domain.dto.model.TransactionModel;

public interface TransactionRepository {

    TransactionModel save(TransactionModel transactionModel);
}
