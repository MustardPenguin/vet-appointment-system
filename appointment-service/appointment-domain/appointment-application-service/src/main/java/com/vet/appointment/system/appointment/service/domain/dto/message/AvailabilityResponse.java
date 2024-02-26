package com.vet.appointment.system.appointment.service.domain.dto.message;

import com.vet.appointment.system.domain.valueobject.AppointmentStatus;

import java.time.ZonedDateTime;
import java.util.UUID;

public class AvailabilityResponse {

    private UUID appointmentId;
    private UUID sagaId;
    private ZonedDateTime createdAt;
    private AppointmentStatus appointmentStatus;
    private String errorMessages;

    private AvailabilityResponse(Builder builder) {
        appointmentId = builder.appointmentId;
        sagaId = builder.sagaId;
        createdAt = builder.createdAt;
        appointmentStatus = builder.appointmentStatus;
        errorMessages = builder.errorMessages;
    }

    public static Builder builder() {
        return new Builder();
    }


    public UUID getSagaId() {
        return sagaId;
    }

    public UUID getAppointmentId() {
        return appointmentId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public String getErrorMessages() {
        return errorMessages;
    }

    public static final class Builder {
        private UUID appointmentId;
        private UUID sagaId;
        private ZonedDateTime createdAt;
        private AppointmentStatus appointmentStatus;
        private String errorMessages;

        private Builder() {
        }

        public Builder appointmentId(UUID val) {
            appointmentId = val;
            return this;
        }

        public Builder sagaId(UUID val) {
            sagaId = val;
            return this;
        }

        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder appointmentStatus(AppointmentStatus val) {
            appointmentStatus = val;
            return this;
        }

        public Builder errorMessages(String val) {
            errorMessages = val;
            return this;
        }

        public AvailabilityResponse build() {
            return new AvailabilityResponse(this);
        }
    }
}
