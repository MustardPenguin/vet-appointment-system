package com.vet.appointment.system.payment.service.domain.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.appointment.system.messaging.event.PaymentAppointmentEventPayload;
import com.vet.appointment.system.payment.service.domain.dto.message.TransactionCreatedEventPayload;
import com.vet.appointment.system.payment.service.domain.dto.message.outbox.PaymentOutboxMessage;
import com.vet.appointment.system.payment.service.domain.dto.model.TransactionModel;
import com.vet.appointment.system.payment.service.domain.event.PaymentEvent;
import com.vet.appointment.system.payment.service.domain.exception.PaymentDomainException;
import com.vet.appointment.system.payment.service.domain.ports.output.repository.PaymentOutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class PaymentOutboxHelper {

    private final PaymentOutboxRepository paymentOutboxRepository;
    private final ObjectMapper objectMapper;

    public PaymentOutboxHelper(PaymentOutboxRepository paymentOutboxRepository, ObjectMapper objectMapper) {
        this.paymentOutboxRepository = paymentOutboxRepository;
        this.objectMapper = objectMapper;
    }

    public void save(PaymentOutboxMessage paymentOutboxMessage) {
        PaymentOutboxMessage response = paymentOutboxRepository.save(paymentOutboxMessage);
        if(response == null) {
            log.error("Could not save PaymentOutboxMessage object for id: {}", paymentOutboxMessage.getId());
            throw new PaymentDomainException("Could not save PaymentOutboxMessage object for id: " + paymentOutboxMessage.getId());
        }
        log.info("Saved PaymentOutboxMessage for id: {}", paymentOutboxMessage.getId());
    }

    public void savePaymentOutboxMessage(TransactionCreatedEventPayload transactionCreatedEventPayload, PaymentEvent paymentEvent) {
        save(PaymentOutboxMessage.builder()
                .payload(createPayload(transactionCreatedEventPayload))
                .createdAt(paymentEvent.getCreatedAt())
                .id(UUID.randomUUID())
                .build());
    }

    private String createPayload(TransactionCreatedEventPayload transactionCreatedEventPayload) {
        try {
            return objectMapper.writeValueAsString(transactionCreatedEventPayload);
        } catch (JsonProcessingException e) {
            log.info("Could not create TransactionCreatedEventPayload object for transaction id: {}", transactionCreatedEventPayload.getId());
            throw new PaymentDomainException("Could not create PaymentAppointmentEventPayload object for transaction id: " + transactionCreatedEventPayload.getId());
        }
    }
}
