package com.vet.appointment.system.appointment.service.domain.outbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public AppointmentAvailabilityEventPayload() {}

    public AppointmentAvailabilityEventPayload(UUID id,
                                               LocalDateTime appointmentStartDateTime,
                                               LocalDateTime appointmentEndDateTime,
                                               ZonedDateTime createdAt) {
        this.id = id;
        this.appointmentStartDateTime = appointmentStartDateTime;
        this.appointmentEndDateTime = appointmentEndDateTime;
        this.createdAt = createdAt;
    }
}
