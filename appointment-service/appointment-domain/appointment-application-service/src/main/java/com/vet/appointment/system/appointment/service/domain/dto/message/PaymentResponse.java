package com.vet.appointment.system.appointment.service.domain.dto.message;

import com.vet.appointment.system.domain.valueobject.PaymentStatus;

import java.time.ZonedDateTime;
import java.util.UUID;

public class PaymentResponse {

    private UUID paymentId;
    private UUID sagaId;
    private String errorMessages;
    private PaymentStatus paymentStatus;
    private ZonedDateTime createdAt;

    private PaymentResponse(Builder builder) {
        paymentId = builder.paymentId;
        sagaId = builder.sagaId;
        errorMessages = builder.errorMessages;
        paymentStatus = builder.paymentStatus;
        createdAt = builder.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public UUID getSagaId() {
        return sagaId;
    }

    public String getErrorMessages() {
        return errorMessages;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public static final class Builder {
        private UUID paymentId;
        private UUID sagaId;
        private String errorMessages;
        private PaymentStatus paymentStatus;
        private ZonedDateTime createdAt;

        private Builder() {
        }

        public Builder paymentId(UUID val) {
            paymentId = val;
            return this;
        }

        public Builder sagaId(UUID val) {
            sagaId = val;
            return this;
        }

        public Builder errorMessages(String val) {
            errorMessages = val;
            return this;
        }

        public Builder paymentStatus(PaymentStatus val) {
            paymentStatus = val;
            return this;
        }

        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        public PaymentResponse build() {
            return new PaymentResponse(this);
        }
    }
}
