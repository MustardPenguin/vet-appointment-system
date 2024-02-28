package com.vet.appointment.system.messaging.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vet.appointment.system.domain.valueobject.AppointmentStatus;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class AvailabilityAppointmentEventPayload {

    @JsonProperty
    private UUID appointmentId;
    @JsonProperty
    private String availabilityId;
    @JsonProperty
    private String errorMessages;
    @JsonProperty
    private ZonedDateTime createdAt;
    @JsonProperty
    private AppointmentStatus appointmentStatus;

    public AvailabilityAppointmentEventPayload() {}

    private AvailabilityAppointmentEventPayload(Builder builder) {
        appointmentId = builder.appointmentId;
        availabilityId = builder.availabilityId;
        errorMessages = builder.errorMessages;
        createdAt = builder.createdAt;
        appointmentStatus = builder.appointmentStatus;
    }

    public static Builder builder() {
        return new Builder();
    }


    public UUID getAppointmentId() {
        return appointmentId;
    }

    public String getErrorMessages() {
        return errorMessages;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public String getAvailabilityId() {
        return availabilityId;
    }

    public static final class Builder {
        private UUID appointmentId;
        private String availabilityId;
        private String errorMessages;
        private ZonedDateTime createdAt;
        private AppointmentStatus appointmentStatus;

        private Builder() {
        }

        public Builder appointmentId(UUID val) {
            appointmentId = val;
            return this;
        }

        public Builder availabilityId(String val) {
            availabilityId = val;
            return this;
        }

        public Builder errorMessages(String val) {
            errorMessages = val;
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

        public AvailabilityAppointmentEventPayload build() {
            return new AvailabilityAppointmentEventPayload(this);
        }
    }
}
