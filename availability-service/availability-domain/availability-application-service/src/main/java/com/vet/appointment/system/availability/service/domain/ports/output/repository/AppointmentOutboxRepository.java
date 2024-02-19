package com.vet.appointment.system.availability.service.domain.ports.output.repository;

import com.vet.appointment.system.availability.service.domain.dto.outbox.AvailabilityAppointmentOutboxMessage;
import com.vet.appointment.system.availability.service.domain.entity.Availability;

import java.util.Optional;

public interface AppointmentOutboxRepository {

    AvailabilityAppointmentOutboxMessage save(AvailabilityAppointmentOutboxMessage availabilityAppointmentOutboxMessage);
}
