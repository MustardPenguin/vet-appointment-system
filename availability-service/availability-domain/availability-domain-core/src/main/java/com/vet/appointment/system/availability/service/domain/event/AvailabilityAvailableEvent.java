package com.vet.appointment.system.availability.service.domain.event;

import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.domain.event.DomainEvent;

import java.time.ZonedDateTime;
import java.util.List;

public class AvailabilityAvailableEvent extends AvailabilityEvent {
    public AvailabilityAvailableEvent(Availability availability, ZonedDateTime createdAt, List<String> errorMessages) {
        super(availability, createdAt, errorMessages);
    }
}
