package com.vet.appointment.system.messaging.event;

import com.vet.appointment.system.domain.valueobject.PaymentStatus;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class PaymentAppointmentEventPayload {

    private UUID paymentId;
    private String errorMessages;
    private PaymentStatus paymentStatus;
    private ZonedDateTime createdAt;

    public PaymentAppointmentEventPayload() {}

    private PaymentAppointmentEventPayload(Builder builder) {
        paymentId = builder.paymentId;
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
        private String errorMessages;
        private PaymentStatus paymentStatus;
        private ZonedDateTime createdAt;

        private Builder() {
        }

        public Builder paymentId(UUID val) {
            paymentId = val;
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

        public PaymentAppointmentEventPayload build() {
            return new PaymentAppointmentEventPayload(this);
        }
    }
}
