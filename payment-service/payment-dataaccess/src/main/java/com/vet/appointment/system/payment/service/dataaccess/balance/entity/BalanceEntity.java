package com.vet.appointment.system.payment.service.dataaccess.balance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "balances")
public class BalanceEntity {

    @Id
    private UUID id;
    private UUID accountId;
    private String email;
    private BigDecimal credit;
    @Version
    private int version;

    public BalanceEntity() {}

    private BalanceEntity(Builder builder) {
        id = builder.id;
        accountId = builder.accountId;
        email = builder.email;
        credit = builder.credit;
        version = builder.version;
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

    public String getEmail() {
        return email;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public int getVersion() {
        return version;
    }

    public static final class Builder {
        private UUID id;
        private UUID accountId;
        private String email;
        private BigDecimal credit;
        private int version;

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

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder credit(BigDecimal val) {
            credit = val;
            return this;
        }

        public Builder version(int val) {
            version = val;
            return this;
        }

        public BalanceEntity build() {
            return new BalanceEntity(this);
        }
    }
}
