package com.vet.appointment.system.appointment.service.domain.ports.input.message.listener;

import com.vet.appointment.system.appointment.service.domain.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {

    void paymentPaid(PaymentResponse paymentResponse);

    void paymentFailed(PaymentResponse paymentResponse);
}
