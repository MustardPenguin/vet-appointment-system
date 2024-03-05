package com.vet.appointment.system.payment.service.domain.dto.message;

import com.vet.appointment.system.domain.valueobject.AccountId;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public class PaymentRequest {

    private UUID appointmentId;
    private UUID accountId;
    private UUID sagaId;
    private BigDecimal cost;
    private String reason;
    private ZonedDateTime createdAt;

    private PaymentRequest(Builder builder) {
        appointmentId = builder.appointmentId;
        accountId = builder.accountId;
        sagaId = builder.sagaId;
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

    public UUID getAccountId() {
        return accountId;
    }

    public static final class Builder {
        private UUID appointmentId;
        private UUID accountId;
        private UUID sagaId;
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

        public Builder sagaId(UUID val) {
            sagaId = val;
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

        public PaymentRequest build() {
            return new PaymentRequest(this);
        }
    }
}
