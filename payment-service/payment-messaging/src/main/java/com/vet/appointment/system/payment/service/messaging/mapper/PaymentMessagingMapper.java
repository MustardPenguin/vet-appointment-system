package com.vet.appointment.system.payment.service.messaging.mapper;

import com.vet.appointment.system.messaging.event.AppointmentPaymentEventPayload;
import com.vet.appointment.system.payment.service.domain.dto.message.PaymentRequest;
import com.vet.appointment.system.payment.service.domain.dto.message.TransactionCreatedEventPayload;
import com.vet.appointment.system.payment.service.domain.dto.model.TransactionModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentMessagingMapper {

    public PaymentRequest paymentEventPayloadToPaymentRequest(AppointmentPaymentEventPayload appointmentPaymentEventPayload, UUID sagaId) {
        return PaymentRequest.builder()
                .appointmentId(appointmentPaymentEventPayload.getAppointmentId())
                .accountId(appointmentPaymentEventPayload.getAccountId())
                .cost(appointmentPaymentEventPayload.getCost())
                .reason(appointmentPaymentEventPayload.getReason())
                .sagaId(sagaId)
                .createdAt(appointmentPaymentEventPayload.getCreatedAt())
                .build();
    }

    public TransactionModel transactionCreatedEventPayloadToTransactionModel(TransactionCreatedEventPayload transactionCreatedEventPayload) {
        return TransactionModel.builder()
                .id(transactionCreatedEventPayload.getId())
                .reason(transactionCreatedEventPayload.getReason())
                .cost(transactionCreatedEventPayload.getCost())
                .accountId(transactionCreatedEventPayload.getAccountId())
                .createdAt(transactionCreatedEventPayload.getCreatedAt())
                .build();
    }
}
