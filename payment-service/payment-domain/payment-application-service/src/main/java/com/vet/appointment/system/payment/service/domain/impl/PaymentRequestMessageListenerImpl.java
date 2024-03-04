package com.vet.appointment.system.payment.service.domain.impl;

import com.vet.appointment.system.payment.service.domain.PaymentDomainService;
import com.vet.appointment.system.payment.service.domain.dto.message.PaymentRequest;
import com.vet.appointment.system.payment.service.domain.dto.model.TransactionModel;
import com.vet.appointment.system.payment.service.domain.entity.Balance;
import com.vet.appointment.system.payment.service.domain.entity.Payment;
import com.vet.appointment.system.payment.service.domain.event.PaymentEvent;
import com.vet.appointment.system.payment.service.domain.helper.PaymentServiceDataHelper;
import com.vet.appointment.system.payment.service.domain.helper.TransactionServiceDataHelper;
import com.vet.appointment.system.payment.service.domain.mapper.PaymentDataMapper;
import com.vet.appointment.system.payment.service.domain.ports.input.message.listener.PaymentRequestMessageListener;
import com.vet.appointment.system.payment.service.domain.ports.output.repository.BalanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.vet.appointment.system.domain.DomainConstants.UTC;

@Slf4j
@Component
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {

    private final TransactionServiceDataHelper transactionServiceDataHelper;
    private final PaymentServiceDataHelper paymentServiceDataHelper;
    private final PaymentDomainService paymentDomainService;
    private final PaymentDataMapper paymentDataMapper;

    public PaymentRequestMessageListenerImpl(TransactionServiceDataHelper transactionServiceDataHelper,
                                             PaymentServiceDataHelper paymentServiceDataHelper,
                                             PaymentDomainService paymentDomainService,
                                             PaymentDataMapper paymentDataMapper) {
        this.transactionServiceDataHelper = transactionServiceDataHelper;
        this.paymentServiceDataHelper = paymentServiceDataHelper;
        this.paymentDomainService = paymentDomainService;
        this.paymentDataMapper = paymentDataMapper;
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
            log.info("Payment request has failed for account id: {}, error messages: {}", paymentRequest.getAccountId(), errorMessages);
            return;
        }
        log.info("Payment request has been paid for account id: {}, Balance = {} - {} - {}",
                paymentRequest.getAccountId(),
                balance.getCredit().add(paymentRequest.getCost()),
                paymentRequest.getCost(),
                balance.getCredit());

        paymentServiceDataHelper.saveBalance(balance);
        TransactionModel transactionModel = paymentDataMapper.paymentToTransactionModel(paymentRequest);
        transactionServiceDataHelper.save(transactionModel, paymentRequest.getSagaId());
    }
}
