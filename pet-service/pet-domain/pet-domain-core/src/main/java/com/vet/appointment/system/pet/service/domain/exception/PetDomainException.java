package com.vet.appointment.system.pet.service.domain.exception;

import com.vet.appointment.system.domain.exception.DomainException;

public class PetDomainException extends DomainException {
    public PetDomainException(String message) {
        super(message);
    }

    public PetDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
