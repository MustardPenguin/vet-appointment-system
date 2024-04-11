package com.vet.appointment.system.payment.service.dataaccess.outbox.payment.mapper;

import com.vet.appointment.system.payment.service.dataaccess.outbox.payment.entity.PaymentOutboxEntity;
import com.vet.appointment.system.payment.service.domain.dto.message.outbox.PaymentOutboxMessage;
import org.springframework.stereotype.Component;

@Component
public class PaymentOutboxDataMapper {

    public PaymentOutboxEntity paymentOutboxMessageTOutboxEntity(PaymentOutboxMessage paymentOutboxMessage) {
        return PaymentOutboxEntity.builder()
                .id(paymentOutboxMessage.getId())
                .payload(paymentOutboxMessage.getPayload())
                .createdAt(paymentOutboxMessage.getCreatedAt())
                .version(paymentOutboxMessage.getVersion())
                .build();
    }

    public PaymentOutboxMessage paymentOutboxEntityToOutboxMessage(PaymentOutboxEntity paymentOutboxEntity) {
        return PaymentOutboxMessage.builder()
                .id(paymentOutboxEntity.getId())
                .payload(paymentOutboxEntity.getPayload())
                .createdAt(paymentOutboxEntity.getCreatedAt())
                .version(paymentOutboxEntity.getVersion())
                .build();
    }
}
