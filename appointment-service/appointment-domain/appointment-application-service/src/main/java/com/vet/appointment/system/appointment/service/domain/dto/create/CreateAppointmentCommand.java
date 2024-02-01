package com.vet.appointment.system.appointment.service.domain.dto.create;

import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateAppointmentCommand {

    @Future(message = "Appointment start date must be in the future!")
    private LocalDateTime appointmentStartDateTime;
    @Future(message = "Appointment end date must be in the future!")
    private LocalDateTime appointmentEndDateTime;
    private UUID ownerId;
    private UUID petId;
    private String description;

    public CreateAppointmentCommand() {}

    public CreateAppointmentCommand(LocalDateTime appointmentStartDateTime,
                                    LocalDateTime appointmentEndDateTime,
                                    UUID petId,
                                    String description) {
        this.appointmentStartDateTime = appointmentStartDateTime;
        this.appointmentEndDateTime = appointmentEndDateTime;
        this.petId = petId;
        this.description = description;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDateTime getAppointmentStartDateTime() {
        return appointmentStartDateTime;
    }

    public LocalDateTime getAppointmentEndDateTime() {
        return appointmentEndDateTime;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public UUID getPetId() {
        return petId;
    }

    public String getDescription() {
        return description;
    }
}
