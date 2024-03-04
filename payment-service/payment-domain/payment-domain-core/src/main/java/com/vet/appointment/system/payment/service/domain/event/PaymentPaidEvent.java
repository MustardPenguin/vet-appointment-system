package com.vet.appointment.system.payment.service.domain.event;

import com.vet.appointment.system.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;
import java.util.List;

public class PaymentPaidEvent extends PaymentEvent {
    public PaymentPaidEvent(Payment payment, ZonedDateTime createdAt, List<String> errorMessages) {
        super(payment, createdAt, errorMessages);
    }
}
