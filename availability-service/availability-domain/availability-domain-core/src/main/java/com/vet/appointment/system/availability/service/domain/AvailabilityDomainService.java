package com.vet.appointment.system.availability.service.domain;

import com.vet.appointment.system.availability.service.domain.entity.Appointment;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityConfirmedEvent;

public interface AvailabilityDomainService {
    AvailabilityConfirmedEvent validateAppointmentAvailability(Appointment appointment);
}
