package com.vet.appointment.system.pet.service.domain.outbox.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.appointment.system.outbox.OutboxStatus;
import com.vet.appointment.system.pet.service.domain.exception.PetDomainException;
import com.vet.appointment.system.pet.service.domain.outbox.model.PetAppointmentEventPayload;
import com.vet.appointment.system.pet.service.domain.outbox.model.PetAppointmentOutboxMessage;
import com.vet.appointment.system.pet.service.domain.ports.output.AppointmentOutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
    public void save(PetAppointmentOutboxMessage petAppointmentOutboxMessage) {
        PetAppointmentOutboxMessage response = appointmentOutboxRepository.save(petAppointmentOutboxMessage);
        if(response == null) {
            log.error("Could not save PetAppointmentOutboxMessage of id: {}", petAppointmentOutboxMessage.getId());
            throw new PetDomainException("Could not save PetAppointmentOutboxMessage of id: " +
                    petAppointmentOutboxMessage.getId());
        }
        log.info("Successfully saved PetAppointmentOutboxMessage of id: {}", petAppointmentOutboxMessage.getId());
    }

    @Transactional
    public void saveAppointmentOutboxMessage(PetAppointmentEventPayload petAppointmentEventPayload,
                                             OutboxStatus outboxStatus) {
        save(PetAppointmentOutboxMessage.builder()
                .id(UUID.randomUUID())
                .createdAt(petAppointmentEventPayload.getCreatedAt())
                .payload(createPayload(petAppointmentEventPayload))
                .outboxStatus(outboxStatus)
                .build());
    }

    private String createPayload(PetAppointmentEventPayload petAppointmentEventPayload) {
        try {
            return objectMapper.writeValueAsString(petAppointmentEventPayload);
        } catch (JsonProcessingException e) {
            log.info("Could not create PetAppointmentEventPayload object for pet id: {}", petAppointmentEventPayload.getId());
            throw new PetDomainException("Could not create PetAppointmentEventPayload object for pet id: " + petAppointmentEventPayload.getId());
        }
    }

}