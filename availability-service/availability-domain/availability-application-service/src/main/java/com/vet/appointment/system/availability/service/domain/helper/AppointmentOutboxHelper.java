package com.vet.appointment.system.availability.service.domain.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.appointment.system.availability.service.domain.dto.outbox.AvailabilityAppointmentOutboxMessage;
import com.vet.appointment.system.availability.service.domain.exception.AvailabilityDomainException;
import com.vet.appointment.system.availability.service.domain.ports.output.repository.AppointmentOutboxRepository;
import com.vet.appointment.system.messaging.event.AvailabilityAppointmentEventPayload;
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

    public AppointmentOutboxHelper(AppointmentOutboxRepository appointmentOutboxRepository, ObjectMapper objectMapper) {
        this.appointmentOutboxRepository = appointmentOutboxRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void save(AvailabilityAppointmentOutboxMessage availabilityAppointmentOutboxMessage) {
        AvailabilityAppointmentOutboxMessage response =
                appointmentOutboxRepository.save(availabilityAppointmentOutboxMessage);
        if(response == null) {
            log.info("Could not saving AvailabilityAppointmentOutboxMessage for id: {}", availabilityAppointmentOutboxMessage.getId());
            throw new AvailabilityDomainException("Could not saving AvailabilityAppointmentOutboxMessage for id: " + availabilityAppointmentOutboxMessage.getId());
        }
        log.info("Successfully saved AvailabilityAppointmentOutboxMessage for id: {}", availabilityAppointmentOutboxMessage.getId());
    }

    @Transactional
    public void saveAppointmentOutboxMessage(AvailabilityAppointmentEventPayload availabilityAppointmentEventPayload,
                                            UUID sagaId) {
        save(AvailabilityAppointmentOutboxMessage.builder()
                .id(UUID.randomUUID())
                .sagaId(sagaId)
                .sagaType(APPOINTMENT_SAGA_NAME)
                .createdAt(availabilityAppointmentEventPayload.getCreatedAt())
                .payload(createPayload(availabilityAppointmentEventPayload))
                .build());
    }

    private String createPayload(AvailabilityAppointmentEventPayload availabilityAppointmentEventPayload) {
        try {
            return objectMapper.writeValueAsString(availabilityAppointmentEventPayload);
        } catch (JsonProcessingException e) {
            log.info("Could not create AvailabilityAppointmentEventPayload object for appointment id: {}", availabilityAppointmentEventPayload.getAppointmentId());
            throw new AvailabilityDomainException("Could not create AvailabilityAppointmentEventPayload object for appointment id: " + availabilityAppointmentEventPayload.getAppointmentId());
        }
    }
}
