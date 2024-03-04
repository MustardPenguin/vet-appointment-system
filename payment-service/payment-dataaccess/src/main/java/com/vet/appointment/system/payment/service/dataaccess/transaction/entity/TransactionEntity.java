package com.vet.appointment.system.payment.service.dataaccess.transaction.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    private UUID id;
    private UUID accountId;
    private BigDecimal cost;
    private String reason;
    private ZonedDateTime createdAt;

    public TransactionEntity() {}

    private TransactionEntity(Builder builder) {
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

        public TransactionEntity build() {
            return new TransactionEntity(this);
        }
    }
}
