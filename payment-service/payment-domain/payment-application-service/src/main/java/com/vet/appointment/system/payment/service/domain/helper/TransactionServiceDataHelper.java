package com.vet.appointment.system.payment.service.domain.helper;

import com.vet.appointment.system.payment.service.domain.dto.model.TransactionModel;
import com.vet.appointment.system.payment.service.domain.exception.PaymentDomainException;
import com.vet.appointment.system.payment.service.domain.ports.output.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class TransactionServiceDataHelper {

    private final TransactionRepository transactionRepository;

    public TransactionServiceDataHelper(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void save(TransactionModel transactionModel, UUID sagaId) {
        TransactionModel response = transactionRepository.save(transactionModel);
        if(response == null) {
            log.error("Transaction could not be saved for saga id {} and account id {}", sagaId, transactionModel.getAccountId());
            throw new PaymentDomainException("Transaction could not be saved for saga id " + sagaId
                    + " and account id " + transactionModel.getAccountId());
        }
        log.info("Transaction has been saved for saga id {} and account id {}", sagaId, transactionModel.getAccountId());
    }
}
