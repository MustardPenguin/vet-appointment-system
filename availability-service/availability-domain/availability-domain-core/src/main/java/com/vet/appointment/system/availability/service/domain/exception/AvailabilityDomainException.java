package com.vet.appointment.system.availability.service.domain.exception;

import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.domain.event.DomainEvent;
import com.vet.appointment.system.domain.exception.DomainException;

import java.time.ZonedDateTime;

public class AvailabilityDomainException extends DomainException {

    public AvailabilityDomainException(String message) {
        super(message);
    }

    public AvailabilityDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
