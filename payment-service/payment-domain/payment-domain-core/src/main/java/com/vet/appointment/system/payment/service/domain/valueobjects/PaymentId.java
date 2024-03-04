package com.vet.appointment.system.payment.service.domain.valueobjects;

import com.vet.appointment.system.domain.valueobject.BaseId;

import java.util.UUID;

public class PaymentId extends BaseId<UUID> {
    public PaymentId(UUID value) {
        super(value);
    }
}
