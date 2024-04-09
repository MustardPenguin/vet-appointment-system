package com.vet.appointment.system.appointment.service.domain.ports.output.repository.outbox;

import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentOutboxMessage;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;

import java.util.Optional;
import java.util.UUID;

public interface AppointmentOutboxRepository {

    AppointmentOutboxMessage save(AppointmentOutboxMessage appointmentOutboxMessage);

    Optional<AppointmentOutboxMessage> findById(UUID id);
}
