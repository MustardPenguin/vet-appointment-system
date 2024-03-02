package com.vet.appointment.system.payment.service.domain.dto.message;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public class PaymentRequest {

    private UUID sagaId;
    private BigDecimal cost;
    private String reason;
    private ZonedDateTime createdAt;

    public PaymentRequest(UUID sagaId, BigDecimal cost, String reason, ZonedDateTime createdAt) {
        this.sagaId = sagaId;
        this.cost = cost;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    public UUID getSagaId() {
        return sagaId;
    }
    public BigDecimal getCost() {
        return cost;
    }

    public String getReason() {
        return reason;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
