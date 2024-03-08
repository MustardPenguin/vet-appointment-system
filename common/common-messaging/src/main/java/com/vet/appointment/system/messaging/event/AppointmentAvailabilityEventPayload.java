package com.vet.appointment.system.messaging.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vet.appointment.system.domain.valueobject.AppointmentStatus;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

public class AppointmentAvailabilityEventPayload {

    @JsonProperty
    private UUID id;
    @JsonProperty
    private LocalDateTime appointmentStartDateTime;
    @JsonProperty
    private LocalDateTime appointmentEndDateTime;
    @JsonProperty
    private ZonedDateTime createdAt;
    @JsonProperty
    private AppointmentStatus appointmentStatus;

    private AppointmentAvailabilityEventPayload(Builder builder) {
        id = builder.id;
        appointmentStartDateTime = builder.appointmentStartDateTime;
        appointmentEndDateTime = builder.appointmentEndDateTime;
        createdAt = builder.createdAt;
        appointmentStatus = builder.appointmentStatus;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getAppointmentStartDateTime() {
        return appointmentStartDateTime;
    }

    public LocalDateTime getAppointmentEndDateTime() {
        return appointmentEndDateTime;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public AppointmentAvailabilityEventPayload() {}

    public static final class Builder {
        private UUID id;
        private LocalDateTime appointmentStartDateTime;
        private LocalDateTime appointmentEndDateTime;
        private ZonedDateTime createdAt;
        private AppointmentStatus appointmentStatus;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder appointmentStartDateTime(LocalDateTime val) {
            appointmentStartDateTime = val;
            return this;
        }

        public Builder appointmentEndDateTime(LocalDateTime val) {
            appointmentEndDateTime = val;
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

        public AppointmentAvailabilityEventPayload build() {
            return new AppointmentAvailabilityEventPayload(this);
        }
    }
}
