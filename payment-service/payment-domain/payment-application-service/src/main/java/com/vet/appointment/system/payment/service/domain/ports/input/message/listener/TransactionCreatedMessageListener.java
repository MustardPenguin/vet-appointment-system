package com.vet.appointment.system.payment.service.domain.ports.input.message.listener;

import com.vet.appointment.system.payment.service.domain.dto.message.TransactionCreatedEventPayload;
import com.vet.appointment.system.payment.service.domain.dto.model.TransactionModel;

public interface TransactionCreatedMessageListener {

    void transactionCreated(TransactionModel transactionModel);
}
