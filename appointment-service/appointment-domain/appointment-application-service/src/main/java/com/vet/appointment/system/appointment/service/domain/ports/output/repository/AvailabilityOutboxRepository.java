package com.vet.appointment.system.appointment.service.domain.ports.output.repository;

import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentAvailabilityOutboxMessage;
import com.vet.appointment.system.saga.SagaStatus;

import java.util.Optional;
import java.util.UUID;

public interface AvailabilityOutboxRepository {

    AppointmentAvailabilityOutboxMessage save(AppointmentAvailabilityOutboxMessage appointmentAvailabilityOutboxMessage);

    Optional<AppointmentAvailabilityOutboxMessage> findBySagaIdAndSagaStatus(String sagaType, UUID sagaId, SagaStatus sagaStatus);
}
