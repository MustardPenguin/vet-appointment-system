package com.vet.appointment.system.payment.service.domain.impl.message.listener;

import com.vet.appointment.system.payment.service.domain.dto.message.TransactionCreatedEventPayload;
import com.vet.appointment.system.payment.service.domain.dto.model.TransactionModel;
import com.vet.appointment.system.payment.service.domain.entity.Balance;
import com.vet.appointment.system.payment.service.domain.exception.PaymentDomainException;
import com.vet.appointment.system.payment.service.domain.helper.PaymentServiceDataHelper;
import com.vet.appointment.system.payment.service.domain.helper.TransactionServiceDataHelper;
import com.vet.appointment.system.payment.service.domain.ports.input.message.listener.TransactionCreatedMessageListener;
import com.vet.appointment.system.payment.service.domain.ports.output.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class TransactionCreatedMessageListenerImpl implements TransactionCreatedMessageListener {

    private final TransactionServiceDataHelper transactionServiceDataHelper;
    private final PaymentServiceDataHelper paymentServiceDataHelper;
    private final TransactionRepository transactionRepository;


    public TransactionCreatedMessageListenerImpl(TransactionServiceDataHelper transactionServiceDataHelper,
                                                 PaymentServiceDataHelper paymentServiceDataHelper,
                                                 TransactionRepository transactionRepository) {
        this.transactionServiceDataHelper = transactionServiceDataHelper;
        this.paymentServiceDataHelper = paymentServiceDataHelper;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public void transactionCreated(TransactionModel transactionModel) {
        log.info("Received transaction event propagation for transaction id: {}", transactionModel.getId());
        TransactionModel response = transactionRepository.findById(transactionModel.getId());
        if(response != null) {
            log.info("Transaction of id: {} already exists in this database, either this instance is the source of the message, or this is a duplicated message!",
                    transactionModel.getId());
            return;
        }

        Balance balance = paymentServiceDataHelper.findBalanceByAccountId(transactionModel.getAccountId());
        balance.subtractCredit(transactionModel.getCost());
        log.info("Payment request has been paid for account id: {}, Balance = {} - {} - {}",
                transactionModel.getAccountId(),
                balance.getCredit().add(transactionModel.getCost()),
                transactionModel.getCost(),
                balance.getCredit());
        paymentServiceDataHelper.saveBalance(balance);
        transactionServiceDataHelper.save(transactionModel);
    }
}
