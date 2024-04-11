package com.vet.appointment.system.payment.service.domain.dto.message;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public class TransactionCreatedEventPayload {

    private UUID id;
    private UUID accountId;
    private BigDecimal cost;
    private String reason;
    private ZonedDateTime createdAt;

    public TransactionCreatedEventPayload() {}

    private TransactionCreatedEventPayload(Builder builder) {
        id = builder.id;
        accountId = builder.accountId;
        cost = builder.cost;
        reason = builder.reason;
        createdAt = builder.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getId() {
        return id;
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
        private UUID id;
        private UUID accountId;
        private BigDecimal cost;
        private String reason;
        private ZonedDateTime createdAt;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
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

        public TransactionCreatedEventPayload build() {
            return new TransactionCreatedEventPayload(this);
        }
    }
}
