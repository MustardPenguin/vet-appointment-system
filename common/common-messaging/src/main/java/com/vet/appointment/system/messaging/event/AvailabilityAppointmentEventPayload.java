package com.vet.appointment.system.messaging.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class AvailabilityAppointmentEventPayload {

    @JsonProperty
    private UUID appointmentId;
    @JsonProperty
    private String errorMessages;
    @JsonProperty
    private ZonedDateTime createdAt;

    public AvailabilityAppointmentEventPayload(UUID appointmentId, String errorMessages, ZonedDateTime createdAt) {
        this.appointmentId = appointmentId;
        this.errorMessages = errorMessages;
        this.createdAt = createdAt;
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
}
