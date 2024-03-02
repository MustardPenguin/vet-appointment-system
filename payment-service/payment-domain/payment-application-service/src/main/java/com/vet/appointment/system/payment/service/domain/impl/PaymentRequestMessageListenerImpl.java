package com.vet.appointment.system.payment.service.domain.impl;

import com.vet.appointment.system.payment.service.domain.dto.message.PaymentRequest;
import com.vet.appointment.system.payment.service.domain.ports.input.message.listener.PaymentRequestMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {

    @Override
    public void paymentRequestReceived(PaymentRequest paymentRequest, UUID accountId) {

        log.info("Processing payment request for account id: {}", accountId);
        
    }
}
