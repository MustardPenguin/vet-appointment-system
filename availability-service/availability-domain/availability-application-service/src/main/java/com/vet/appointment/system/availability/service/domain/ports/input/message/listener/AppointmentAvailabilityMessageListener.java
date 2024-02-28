package com.vet.appointment.system.availability.service.domain.ports.input.message.listener;

import com.vet.appointment.system.availability.service.domain.dto.message.AvailabilityRequest;
import com.vet.appointment.system.availability.service.domain.entity.Appointment;

import java.util.UUID;

public interface AppointmentAvailabilityMessageListener {

    void checkAvailability(AvailabilityRequest availabilityRequest, UUID appointmentId);
}
