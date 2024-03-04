package com.vet.appointment.system.payment.service.domain.entity;

import com.vet.appointment.system.domain.entity.AggregateRoot;
import com.vet.appointment.system.domain.valueobject.PaymentStatus;
import com.vet.appointment.system.payment.service.domain.valueobjects.PaymentId;

import java.math.BigDecimal;
import java.util.UUID;

public class Payment extends AggregateRoot<PaymentId> {

    private UUID accountId;
    private BigDecimal cost;
    private String reason;
    private PaymentStatus paymentStatus;

    private Payment(Builder builder) {
        super.setId(new PaymentId(builder.id));
        accountId = builder.accountId;
        cost = builder.cost;
        reason = builder.reason;
        setPaymentStatus(builder.paymentStatus);
    }

    public static Builder builder() {
        return new Builder();
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }


    public static final class Builder {
        private UUID id;
        private UUID accountId;
        private BigDecimal cost;
        private String reason;
        private PaymentStatus paymentStatus;

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

        public Builder paymentStatus(PaymentStatus val) {
            paymentStatus = val;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
