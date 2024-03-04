package com.vet.appointment.system.payment.service.domain.event;

import com.vet.appointment.system.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;
import java.util.List;

public class PaymentFailedEvent extends PaymentEvent {
    public PaymentFailedEvent(Payment payment, ZonedDateTime createdAt, List<String> errorMessages) {
        super(payment, createdAt, errorMessages);
    }
}
