package com.vet.appointment.system.payment.service.domain.impl;

import com.vet.appointment.system.payment.service.domain.PaymentDomainService;
import com.vet.appointment.system.payment.service.domain.dto.message.PaymentRequest;
import com.vet.appointment.system.payment.service.domain.entity.Balance;
import com.vet.appointment.system.payment.service.domain.entity.Payment;
import com.vet.appointment.system.payment.service.domain.event.PaymentEvent;
import com.vet.appointment.system.payment.service.domain.helper.PaymentServiceDataHelper;
import com.vet.appointment.system.payment.service.domain.ports.input.message.listener.PaymentRequestMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {

    private final PaymentServiceDataHelper paymentServiceDataHelper;
    private final PaymentDomainService paymentDomainService;

    public PaymentRequestMessageListenerImpl(PaymentServiceDataHelper paymentServiceDataHelper,
                                             PaymentDomainService paymentDomainService) {
        this.paymentServiceDataHelper = paymentServiceDataHelper;
        this.paymentDomainService = paymentDomainService;
    }

    @Override
    @Transactional
    public void paymentRequestReceived(PaymentRequest paymentRequest) {

        log.info("Processing payment request for account id: {}", paymentRequest.getAccountId());
        Balance balance = paymentServiceDataHelper.findBalanceByAccountId(paymentRequest.getAccountId());
        Payment payment = new Payment(
                paymentRequest.getAccountId(),
                paymentRequest.getCost(),
                paymentRequest.getReason());
        List<String> errorMessages = new ArrayList<>();
        PaymentEvent paymentEvent = paymentDomainService.validatePaymentRequest(payment, balance, errorMessages);

        if(!errorMessages.isEmpty()) {
            log.info("Payment request has failed for account id: {}", paymentRequest.getAccountId());
            log.error("Error messages: {}", errorMessages);
            return;
        }
        log.info("Payment request has been paid for account id: {}", paymentRequest.getAccountId());
    }
}
