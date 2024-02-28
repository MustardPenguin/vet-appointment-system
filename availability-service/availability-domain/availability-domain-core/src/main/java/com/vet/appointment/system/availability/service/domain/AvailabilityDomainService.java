package com.vet.appointment.system.availability.service.domain;

import com.vet.appointment.system.availability.service.domain.entity.Appointment;
import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityAvailableEvent;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityEvent;

import java.util.List;
import java.util.Optional;

public interface AvailabilityDomainService {
    AvailabilityEvent validateAvailability(Availability availability,
                                                      Optional<List<Availability>> availabilities,
                                                      List<String> errorMessages);
}
