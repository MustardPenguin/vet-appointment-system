package com.vet.appointment.system.appointment.service.domain.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.appointment.system.appointment.service.domain.exception.AppointmentDomainException;
import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentAvailabilityOutboxMessage;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.outbox.AvailabilityOutboxRepository;
import com.vet.appointment.system.messaging.event.AppointmentAvailabilityEventPayload;
import com.vet.appointment.system.messaging.event.AppointmentPaymentEventPayload;
import com.vet.appointment.system.saga.SagaStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.vet.appointment.system.saga.SagaConstants.APPOINTMENT_SAGA_NAME;

@Slf4j
@Component
public class AvailabilityOutboxHelper {

    private final AvailabilityOutboxRepository availabilityOutboxRepository;
    private final OutboxHelper<AppointmentAvailabilityEventPayload> outboxHelper;
    private final AppointmentOutboxHelper appointmentOutboxHelper;

    public AvailabilityOutboxHelper(AvailabilityOutboxRepository availabilityOutboxRepository,
                                    OutboxHelper<AppointmentAvailabilityEventPayload> outboxHelper,
                                    AppointmentOutboxHelper appointmentOutboxHelper) {
        this.availabilityOutboxRepository = availabilityOutboxRepository;
        this.outboxHelper = outboxHelper;
        this.appointmentOutboxHelper = appointmentOutboxHelper;
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
                                              SagaStatus sagaStatus,
                                              UUID sagaId) {
        save(AppointmentAvailabilityOutboxMessage.builder()
                .id(UUID.randomUUID())
                .createdAt(appointmentAvailabilityEventPayload.getCreatedAt())
                .payload(outboxHelper.createPayload(appointmentAvailabilityEventPayload, appointmentAvailabilityEventPayload.getId()))
                .sagaStatus(sagaStatus)
                .sagaId(sagaId)
                .sagaType(APPOINTMENT_SAGA_NAME)
                .build());
    }

    public Optional<AppointmentAvailabilityOutboxMessage> findAvailabilityOutboxMessageBySagaIdAndSagaStatus(UUID sagaId, SagaStatus... sagaStatuses) {
        return availabilityOutboxRepository.findBySagaIdAndSagaStatus(APPOINTMENT_SAGA_NAME, sagaId, sagaStatuses);
    }
}
