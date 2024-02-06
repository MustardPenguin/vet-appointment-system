package com.vet.appointment.system.appointment.service.domain.ports.output.repository;

import com.vet.appointment.system.appointment.service.domain.outbox.model.AppointmentAvailabilityOutboxMessage;

public interface AvailabilityOutboxRepository {

    AppointmentAvailabilityOutboxMessage save(AppointmentAvailabilityOutboxMessage appointmentAvailabilityOutboxMessage);
}
