package com.vet.appointment.system.messaging.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vet.appointment.system.domain.valueobject.PaymentStatus;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class PaymentAppointmentEventPayload {

    @JsonProperty
    private UUID appointmentId;
    @JsonProperty
    private UUID paymentId;
    @JsonProperty
    private String errorMessages;
    @JsonProperty
    private PaymentStatus paymentStatus;
    @JsonProperty
    private ZonedDateTime createdAt;

    public PaymentAppointmentEventPayload() {}

    private PaymentAppointmentEventPayload(Builder builder) {
        appointmentId = builder.appointmentId;
        paymentId = builder.paymentId;
        errorMessages = builder.errorMessages;
        paymentStatus = builder.paymentStatus;
        createdAt = builder.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getAppointmentId() {
        return appointmentId;
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
        private UUID appointmentId;
        private UUID paymentId;
        private String errorMessages;
        private PaymentStatus paymentStatus;
        private ZonedDateTime createdAt;

        private Builder() {
        }

        public Builder appointmentId(UUID val) {
            appointmentId = val;
            return this;
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
