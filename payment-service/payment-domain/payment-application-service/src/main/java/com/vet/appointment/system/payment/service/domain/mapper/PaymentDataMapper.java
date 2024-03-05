package com.vet.appointment.system.payment.service.domain.mapper;

import com.vet.appointment.system.domain.valueobject.PaymentStatus;
import com.vet.appointment.system.messaging.event.PaymentAppointmentEventPayload;
import com.vet.appointment.system.payment.service.domain.dto.message.PaymentRequest;
import com.vet.appointment.system.payment.service.domain.dto.model.TransactionModel;
import com.vet.appointment.system.payment.service.domain.entity.Payment;
import com.vet.appointment.system.payment.service.domain.event.PaymentEvent;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.vet.appointment.system.domain.DomainConstants.UTC;

@Component
public class PaymentDataMapper {

    public Payment paymentRequestToPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(UUID.randomUUID())
                .cost(paymentRequest.getCost())
                .accountId(paymentRequest.getAccountId())
                .paymentStatus(PaymentStatus.PENDING)
                .reason(paymentRequest.getReason())
                .build();
    }

    public TransactionModel paymentToTransactionModel(PaymentRequest paymentRequest) {
        return TransactionModel.builder()
                .id(UUID.randomUUID())
                .createdAt(ZonedDateTime.now(ZoneId.of(UTC)))
                .accountId(paymentRequest.getAccountId())
                .cost(paymentRequest.getCost())
                .reason(paymentRequest.getReason())
                .build();
    }

    public PaymentAppointmentEventPayload paymentEventToPaymentAppointmentEventPayload(PaymentEvent paymentEvent,
                                                                                       UUID appointmentId) {
        return PaymentAppointmentEventPayload.builder()
                .appointmentId(appointmentId)
                .paymentId(paymentEvent.getEntity().getId().getValue())
                .errorMessages(String.join(", ", paymentEvent.getErrorMessages()))
                .createdAt(paymentEvent.getCreatedAt())
                .paymentStatus(paymentEvent.getEntity().getPaymentStatus())
                .build();
    }
}
