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
    private String errorMessages;
    @JsonProperty
    private ZonedDateTime createdAt;
    @JsonProperty
    private AppointmentStatus appointmentStatus;

    public AvailabilityAppointmentEventPayload(UUID appointmentId, String errorMessages, ZonedDateTime createdAt, AppointmentStatus appointmentStatus) {
        this.appointmentId = appointmentId;
        this.errorMessages = errorMessages;
        this.createdAt = createdAt;
        this.appointmentStatus = appointmentStatus;
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
}
