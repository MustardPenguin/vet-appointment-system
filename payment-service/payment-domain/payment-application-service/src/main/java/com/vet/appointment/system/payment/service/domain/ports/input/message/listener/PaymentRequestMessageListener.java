package com.vet.appointment.system.payment.service.domain.ports.input.message.listener;

import com.vet.appointment.system.payment.service.domain.dto.message.PaymentRequest;

import java.util.UUID;

public interface PaymentRequestMessageListener {

    void paymentRequestReceived(PaymentRequest paymentRequest, UUID accountId);
}
