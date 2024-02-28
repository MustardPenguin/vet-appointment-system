package com.vet.appointment.system.availability.service.domain.event;

import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.domain.event.DomainEvent;

import java.time.ZonedDateTime;
import java.util.List;

public class AvailabilityEvent extends DomainEvent<Availability> {

    private List<String> errorMessages;

    public AvailabilityEvent(Availability availability, ZonedDateTime createdAt, List<String> errorMessages) {
        super(availability, createdAt);
        this.errorMessages = errorMessages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
