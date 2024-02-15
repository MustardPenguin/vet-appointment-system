package com.vet.appointment.system.availability.service.domain;

import com.vet.appointment.system.availability.service.domain.entity.Appointment;
import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityConfirmedEvent;

import java.util.List;

public interface AvailabilityDomainService {
    AvailabilityConfirmedEvent validateAppointmentAvailability(Appointment appointment,
                                                               List<Availability> availabilities,
                                                               List<String> errorMessages);
}
