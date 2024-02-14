package com.vet.appointment.system.availability.service.domain.event;

import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.domain.event.DomainEvent;

import java.time.ZonedDateTime;

public class AvailabilityConfirmedEvent extends DomainEvent<Availability> {
    public AvailabilityConfirmedEvent(Availability availability, ZonedDateTime createdAt) {
        super(availability, createdAt);
    }
}
