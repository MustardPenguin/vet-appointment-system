package com.vet.appointment.system.payment.service.domain.ports.output.repository;

import com.vet.appointment.system.payment.service.domain.dto.message.outbox.PaymentOutboxMessage;
import com.vet.appointment.system.payment.service.domain.dto.model.TransactionModel;

public interface PaymentOutboxRepository {

    PaymentOutboxMessage save(PaymentOutboxMessage paymentOutboxMessage);
}
