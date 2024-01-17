package com.vet.appointment.system.appointment.service.domain.dto.create;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateAppointmentCommand {

    private LocalDateTime appointmentStartDateTime;
    private LocalDateTime appointmentEndDateTime;
    private UUID ownerId;
    private UUID petId;
    private String description;

    public CreateAppointmentCommand() {}

    public CreateAppointmentCommand(LocalDateTime appointmentStartDateTime,
                                    LocalDateTime appointmentEndDateTime,
                                    UUID ownerId,
                                    UUID petId,
                                    String description) {
        this.appointmentStartDateTime = appointmentStartDateTime;
        this.appointmentEndDateTime = appointmentEndDateTime;
        this.ownerId = ownerId;
        this.petId = petId;
        this.description = description;
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
