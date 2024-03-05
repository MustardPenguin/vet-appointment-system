package com.vet.appointment.system.appointment.service.domain.impl;

import com.vet.appointment.system.appointment.service.domain.dto.message.PaymentResponse;
import com.vet.appointment.system.appointment.service.domain.ports.input.message.listener.PaymentResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {
    @Override
    public void paymentPaid(PaymentResponse paymentResponse) {
        log.info("Payment paid for appointment with saga id: {}", paymentResponse.getSagaId());
    }

    @Override
    public void paymentFailed(PaymentResponse paymentResponse) {
        log.info("Payment failed for appointment with saga id: {}", paymentResponse.getSagaId());
    }
}
