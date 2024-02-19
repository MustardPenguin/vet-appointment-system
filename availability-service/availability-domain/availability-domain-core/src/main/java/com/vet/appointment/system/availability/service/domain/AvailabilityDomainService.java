package com.vet.appointment.system.availability.service.domain;

import com.vet.appointment.system.availability.service.domain.entity.Appointment;
import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityConfirmedEvent;

import java.util.List;
import java.util.Optional;

public interface AvailabilityDomainService {
    AvailabilityConfirmedEvent validateAppointmentAvailability(Appointment appointment,
                                                               Optional<List<Availability>> availabilities,
                                                               List<String> errorMessages);
}
