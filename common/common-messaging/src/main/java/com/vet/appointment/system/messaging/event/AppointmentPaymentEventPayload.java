package com.vet.appointment.system.messaging.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public class AppointmentPaymentEventPayload {

    @JsonProperty
    private UUID accountId;
    @JsonProperty
    private BigDecimal cost;
    @JsonProperty
    private String reason;
    @JsonProperty
    private ZonedDateTime createdAt;

    public AppointmentPaymentEventPayload() {}

    public AppointmentPaymentEventPayload(UUID accountId, BigDecimal cost, String reason, ZonedDateTime createdAt) {
        this.accountId = accountId;
        this.cost = cost;
        this.reason = reason;
        this.createdAt = createdAt;
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

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
