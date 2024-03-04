package com.vet.appointment.system.payment.service.domain.mapper;

import com.vet.appointment.system.payment.service.domain.dto.message.PaymentRequest;
import com.vet.appointment.system.payment.service.domain.dto.model.TransactionModel;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.vet.appointment.system.domain.DomainConstants.UTC;

@Component
public class PaymentDataMapper {

    public TransactionModel paymentToTransactionModel(PaymentRequest paymentRequest) {
        return TransactionModel.builder()
                .id(UUID.randomUUID())
                .createdAt(ZonedDateTime.now(ZoneId.of(UTC)))
                .accountId(paymentRequest.getAccountId())
                .cost(paymentRequest.getCost())
                .reason(paymentRequest.getReason())
                .build();
    }
}
