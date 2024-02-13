package com.vet.appointment.system.account.service.domain.outbox.scheduler.appointment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.appointment.service.account.service.domain.exception.AccountDomainException;
import com.vet.appointment.system.messaging.event.AccountAppointmentEventPayload;
import com.vet.appointment.system.account.service.domain.outbox.model.AccountAppointmentOutboxMessage;
import com.vet.appointment.system.account.service.domain.ports.output.repository.AppointmentOutboxRepository;
import com.vet.appointment.system.outbox.OutboxStatus;
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
    public void save(AccountAppointmentOutboxMessage accountAppointmentOutboxMessage) {
        AccountAppointmentOutboxMessage response = appointmentOutboxRepository.save(accountAppointmentOutboxMessage);
        if(response == null) {
            log.error("Could not save AccountAppointmentOutboxMessage of id: {}", accountAppointmentOutboxMessage.getId());
            throw new AccountDomainException("Could not save AccountAppointmentOutboxMessage of id: " +
                    accountAppointmentOutboxMessage.getId());
        }
        log.info("Successfully saved AccountAppointmentOutboxMessage of id: {}", accountAppointmentOutboxMessage.getId());
    }

    @Transactional
    public void saveAppointmentOutboxMessage(AccountAppointmentEventPayload accountAppointmentEventPayload,
                                             OutboxStatus outboxStatus) {
        save(AccountAppointmentOutboxMessage.builder()
                .id(UUID.randomUUID())
                .createdAt(accountAppointmentEventPayload.getCreatedAt())
                .payload(createPayload(accountAppointmentEventPayload))
                .outboxStatus(outboxStatus)
                .build());
    }

    private String createPayload(AccountAppointmentEventPayload accountAppointmentEventPayload) {
        try {
            return objectMapper.writeValueAsString(accountAppointmentEventPayload);
        } catch (JsonProcessingException e) {
            log.info("Could not create AccountAppointmentEventPayload object for account id: {}", accountAppointmentEventPayload.getId());
            throw new AccountDomainException("Could not create AccountAppointmentEventPayload object for account id: " + accountAppointmentEventPayload.getId());
        }
    }

}
