package com.vet.appointment.system.payment.service.domain.entity;

import com.vet.appointment.system.domain.entity.AggregateRoot;
import com.vet.appointment.system.payment.service.domain.valueobjects.PaymentId;

import java.math.BigDecimal;
import java.util.UUID;

public class Payment extends AggregateRoot<PaymentId> {

    private UUID accountId;
    private BigDecimal amount;
    private String reason;

}
