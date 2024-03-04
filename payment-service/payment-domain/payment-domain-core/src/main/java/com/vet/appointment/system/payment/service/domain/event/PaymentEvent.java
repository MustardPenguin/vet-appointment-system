package com.vet.appointment.system.payment.service.domain.event;

import com.vet.appointment.system.domain.event.DomainEvent;
import com.vet.appointment.system.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;
import java.util.List;

public class PaymentEvent extends DomainEvent<Payment> {

    private final List<String> errorMessages;

    public PaymentEvent(Payment payment, ZonedDateTime createdAt, List<String> errorMessages) {
        super(payment, createdAt);
        this.errorMessages = errorMessages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
