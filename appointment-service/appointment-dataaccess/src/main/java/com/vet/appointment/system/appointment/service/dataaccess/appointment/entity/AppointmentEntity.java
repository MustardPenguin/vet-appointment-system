package com.vet.appointment.system.appointment.service.dataaccess.appointment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointments")
public class AppointmentEntity {

    @Id
    private UUID id;
    private UUID ownerId;
    private UUID petId;
    private String description;
    private LocalDateTime appointmentStartDateTime;
    private LocalDateTime appointmentEndDateTime;

    public AppointmentEntity() {}

    private AppointmentEntity(Builder builder) {
        id = builder.id;
        ownerId = builder.ownerId;
        petId = builder.petId;
        description = builder.description;
        appointmentStartDateTime = builder.appointmentStartDateTime;
        appointmentEndDateTime = builder.appointmentEndDateTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getId() {
        return id;
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

    public LocalDateTime getAppointmentStartDateTime() {
        return appointmentStartDateTime;
    }

    public LocalDateTime getAppointmentEndDateTime() {
        return appointmentEndDateTime;
    }


    public static final class Builder {
        private UUID id;
        private UUID ownerId;
        private UUID petId;
        private String description;
        private LocalDateTime appointmentStartDateTime;
        private LocalDateTime appointmentEndDateTime;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder ownerId(UUID val) {
            ownerId = val;
            return this;
        }

        public Builder petId(UUID val) {
            petId = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
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

        public AppointmentEntity build() {
            return new AppointmentEntity(this);
        }
    }
}
