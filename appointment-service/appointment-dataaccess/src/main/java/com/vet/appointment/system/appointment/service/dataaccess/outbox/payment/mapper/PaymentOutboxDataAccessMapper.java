package com.vet.appointment.system.appointment.service.dataaccess.outbox.payment.mapper;

import com.vet.appointment.system.appointment.service.dataaccess.outbox.payment.entity.PaymentOutboxEntity;
import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentPaymentOutboxMessage;
import org.springframework.stereotype.Component;

@Component
public class PaymentOutboxDataAccessMapper {

    public PaymentOutboxEntity paymentOutboxMessageToOutboxEntity(AppointmentPaymentOutboxMessage appointmentPaymentOutboxMessage) {
        return PaymentOutboxEntity.builder()
                .id(appointmentPaymentOutboxMessage.getId())
                .createdAt(appointmentPaymentOutboxMessage.getCreatedAt())
                .payload(appointmentPaymentOutboxMessage.getPayload())
                .sagaId(appointmentPaymentOutboxMessage.getSagaId())
                .sagaType(appointmentPaymentOutboxMessage.getSagaType())
                .sagaStatus(appointmentPaymentOutboxMessage.getSagaStatus())
                .version(appointmentPaymentOutboxMessage.getVersion())
                .build();
    }

    public AppointmentPaymentOutboxMessage outboxEntityToPaymentOutboxMessage(PaymentOutboxEntity paymentOutboxEntity) {
        return AppointmentPaymentOutboxMessage.builder()
                .id(paymentOutboxEntity.getId())
                .createdAt(paymentOutboxEntity.getCreatedAt())
                .payload(paymentOutboxEntity.getPayload())
                .sagaId(paymentOutboxEntity.getSagaId())
                .sagaType(paymentOutboxEntity.getSagaType())
                .sagaStatus(paymentOutboxEntity.getSagaStatus())
                .version(paymentOutboxEntity.getVersion())
                .build();
    }
}
