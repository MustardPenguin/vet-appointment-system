package com.vet.appointment.system.messaging.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public class AppointmentPaymentEventPayload {

    @JsonProperty
    private UUID appointmentId;
    @JsonProperty
    private UUID accountId;
    @JsonProperty
    private BigDecimal cost;
    @JsonProperty
    private String reason;
    @JsonProperty
    private ZonedDateTime createdAt;

    public AppointmentPaymentEventPayload() {}

    private AppointmentPaymentEventPayload(Builder builder) {
        appointmentId = builder.appointmentId;
        accountId = builder.accountId;
        cost = builder.cost;
        reason = builder.reason;
        createdAt = builder.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getAppointmentId() {
        return appointmentId;
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


    public static final class Builder {
        private UUID appointmentId;
        private UUID accountId;
        private BigDecimal cost;
        private String reason;
        private ZonedDateTime createdAt;

        private Builder() {
        }

        public Builder appointmentId(UUID val) {
            appointmentId = val;
            return this;
        }

        public Builder accountId(UUID val) {
            accountId = val;
            return this;
        }

        public Builder cost(BigDecimal val) {
            cost = val;
            return this;
        }

        public Builder reason(String val) {
            reason = val;
            return this;
        }

        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        public AppointmentPaymentEventPayload build() {
            return new AppointmentPaymentEventPayload(this);
        }
    }
}
