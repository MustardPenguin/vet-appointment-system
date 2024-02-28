package com.vet.appointment.system.availability.service.domain.event;

import com.vet.appointment.system.availability.service.domain.entity.Availability;

import java.time.ZonedDateTime;
import java.util.List;

public class AvailabilityUnavailableEvent extends AvailabilityEvent {
    public AvailabilityUnavailableEvent(Availability availability, ZonedDateTime createdAt, List<String> errorMessages) {
        super(availability, createdAt, errorMessages);
    }
}
