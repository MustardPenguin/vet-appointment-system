package com.vet.appointment.system.appointment.service.domain.dto.create;

import com.vet.appointment.system.domain.dto.ResponseMessage;

import java.time.ZonedDateTime;
import java.util.UUID;

public class CreateAppointmentResponse extends ResponseMessage {
    private UUID appointmentId;
    private ZonedDateTime createdAt;

    public CreateAppointmentResponse(String message, int statusCode, UUID appointmentId, ZonedDateTime createdAt) {
        super(message, statusCode);
        this.appointmentId = appointmentId;
        this.createdAt = createdAt;
    }

    public UUID getAppointmentId() {
        return appointmentId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
