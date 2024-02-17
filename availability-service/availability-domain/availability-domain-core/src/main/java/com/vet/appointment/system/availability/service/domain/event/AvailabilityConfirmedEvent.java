package com.vet.appointment.system.availability.service.domain.event;

import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.domain.event.DomainEvent;

import java.time.ZonedDateTime;
import java.util.List;

public class AvailabilityConfirmedEvent extends DomainEvent<Availability> {

    private final List<String> errorMessages;

    public AvailabilityConfirmedEvent(Availability availability, ZonedDateTime createdAt, List<String> errorMessages) {
        super(availability, createdAt);
        this.errorMessages = errorMessages;
    }
}
