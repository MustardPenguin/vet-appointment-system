package com.vet.appointment.system.payment.service.domain.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.appointment.system.messaging.event.AvailabilityAppointmentEventPayload;
import com.vet.appointment.system.messaging.event.PaymentAppointmentEventPayload;
import com.vet.appointment.system.payment.service.domain.dto.message.outbox.PaymentAppointmentOutboxMessage;
import com.vet.appointment.system.payment.service.domain.exception.PaymentDomainException;
import com.vet.appointment.system.payment.service.domain.mapper.PaymentDataMapper;
import com.vet.appointment.system.payment.service.domain.ports.output.repository.AppointmentOutboxRepository;
import com.vet.appointment.system.saga.SagaStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.vet.appointment.system.saga.SagaConstants.APPOINTMENT_SAGA_NAME;

@Slf4j
@Component
public class AppointmentOutboxHelper {

    private final AppointmentOutboxRepository appointmentOutboxRepository;
    private final ObjectMapper objectMapper;

    public AppointmentOutboxHelper(AppointmentOutboxRepository appointmentOutboxRepository,
                                   ObjectMapper objectMapper) {
        this.appointmentOutboxRepository = appointmentOutboxRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void save(PaymentAppointmentOutboxMessage paymentAppointmentOutboxMessage) {
        PaymentAppointmentOutboxMessage response = appointmentOutboxRepository.save(paymentAppointmentOutboxMessage);
        if(response == null) {
            log.error("Could not save PaymentAppointmentOutboxMessage object for id: {}", paymentAppointmentOutboxMessage.getId());
            throw new PaymentDomainException("Could not save PaymentAppointmentOutboxMessage object for id: " + paymentAppointmentOutboxMessage.getId());
        }
        log.info("Saved PaymentAppointmentOutboxMessage for id: {}", paymentAppointmentOutboxMessage.getId());
    }

    @Transactional
    public void saveAppointmentOutboxMessage(PaymentAppointmentEventPayload paymentAppointmentEventPayload,
                                             UUID sagaId) {
        save(PaymentAppointmentOutboxMessage.builder()
                .id(UUID.randomUUID())
                .sagaType(APPOINTMENT_SAGA_NAME)
                .sagaId(sagaId)
                .createdAt(paymentAppointmentEventPayload.getCreatedAt())
                .payload(createPayload(paymentAppointmentEventPayload))
                .build());
    }

    private String createPayload(PaymentAppointmentEventPayload paymentAppointmentEventPayload) {
        try {
            return objectMapper.writeValueAsString(paymentAppointmentEventPayload);
        } catch (JsonProcessingException e) {
            log.info("Could not create PaymentAppointmentEventPayload object for payment id: {}", paymentAppointmentEventPayload.getPaymentId());
            throw new PaymentDomainException("Could not create PaymentAppointmentEventPayload object for payment id: " + paymentAppointmentEventPayload.getPaymentId());
        }
    }
}
