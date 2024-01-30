package com.vet.appointment.system.appointment.service.domain.exception;

import com.vet.appointment.system.domain.exception.DomainException;

public class AppointmentDomainException extends DomainException {
    public AppointmentDomainException(String message) {
        super(message);
    }

    public AppointmentDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
