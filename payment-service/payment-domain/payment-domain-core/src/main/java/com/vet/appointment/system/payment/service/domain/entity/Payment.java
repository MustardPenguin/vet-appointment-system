package com.vet.appointment.system.payment.service.domain.entity;

import com.vet.appointment.system.domain.entity.AggregateRoot;
import com.vet.appointment.system.payment.service.domain.valueobjects.PaymentId;

import java.math.BigDecimal;
import java.util.UUID;

public class Payment extends AggregateRoot<PaymentId> {

    private UUID accountId;
    private BigDecimal cost;
    private String reason;

    public Payment(UUID accountId, BigDecimal amount, String reason) {
        this.accountId = accountId;
        this.cost = amount;
        this.reason = reason;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public String getReason() {
        return reason;
    }
}
