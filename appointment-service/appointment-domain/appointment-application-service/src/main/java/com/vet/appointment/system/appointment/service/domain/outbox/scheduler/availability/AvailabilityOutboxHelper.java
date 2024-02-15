package com.vet.appointment.system.appointment.service.domain.outbox.scheduler.availability;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.appointment.system.appointment.service.domain.exception.AppointmentDomainException;
import com.vet.appointment.system.appointment.service.domain.outbox.model.AppointmentAvailabilityOutboxMessage;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.AvailabilityOutboxRepository;
import com.vet.appointment.system.messaging.event.AppointmentAvailabilityEventPayload;
import com.vet.appointment.system.outbox.OutboxStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class AvailabilityOutboxHelper {

    private final AvailabilityOutboxRepository availabilityOutboxRepository;
    private final ObjectMapper objectMapper;

    public AvailabilityOutboxHelper(AvailabilityOutboxRepository availabilityOutboxRepository,
                                    ObjectMapper objectMapper) {
        this.availabilityOutboxRepository = availabilityOutboxRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void save(AppointmentAvailabilityOutboxMessage appointmentAvailabilityOutboxMessage) {
        AppointmentAvailabilityOutboxMessage response = availabilityOutboxRepository.save(appointmentAvailabilityOutboxMessage);
        if(response == null) {
            log.error("Could not save AppointmentAvailabilityOutboxMessage of id: {}", appointmentAvailabilityOutboxMessage.getId());
            throw new AppointmentDomainException("Could not save AppointmentAvailabilityOutboxMessage of id: " +
                    appointmentAvailabilityOutboxMessage.getId());
        }
        log.info("Successfully saved AppointmentAvailabilityOutboxMessage of id: {}", appointmentAvailabilityOutboxMessage.getId());
    }

    @Transactional
    public void saveAvailabilityOutboxMessage(AppointmentAvailabilityEventPayload appointmentAvailabilityEventPayload,
                                              OutboxStatus outboxStatus) {
        save(AppointmentAvailabilityOutboxMessage.builder()
                .id(UUID.randomUUID())
                .createdAt(appointmentAvailabilityEventPayload.getCreatedAt())
                .payload(createPayload(appointmentAvailabilityEventPayload))
                .outboxStatus(outboxStatus)
                .build());
    }

    private String createPayload(AppointmentAvailabilityEventPayload appointmentAvailabilityEventPayload) {
        try {
            return objectMapper.writeValueAsString(appointmentAvailabilityEventPayload);
        } catch (JsonProcessingException e) {
            log.info("Could not create AppointmentAvailabilityEventPayload object for account id: {}", appointmentAvailabilityEventPayload.getId());
            throw new AppointmentDomainException("Could not create AppointmentAvailabilityEventPayload object for account id: " + appointmentAvailabilityEventPayload.getId());
        }
    }
}
