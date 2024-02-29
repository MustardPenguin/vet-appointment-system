package com.vet.appointment.system.appointment.service.domain.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.appointment.system.appointment.service.domain.exception.AppointmentDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class OutboxHelper<T> {

    private final ObjectMapper objectMapper;

    public OutboxHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String createPayload(T eventPayload, UUID accountId) {
        try {
            return objectMapper.writeValueAsString(eventPayload);
        } catch (JsonProcessingException e) {
            log.error("Could not create {} object for account id {}", eventPayload.getClass(), accountId);
            throw new AppointmentDomainException("Error while creating payload", e);
        }
    }
}
