package com.vet.appointment.system.appointment.service.domain.entity;

import com.vet.appointment.system.domain.entity.AggregateRoot;
import com.vet.appointment.system.domain.valueobject.AppointmentId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment extends AggregateRoot<AppointmentId> {

    private final LocalDateTime appointmentStartDateTime;
    private final LocalDateTime appointmentEndDateTime;
    private final UUID ownerId;
    private final UUID petId;
    private final String description;

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

    private Appointment(Builder builder) {
        super.setId(new AppointmentId(builder.id));
        appointmentStartDateTime = builder.appointmentStartDateTime;
        appointmentEndDateTime = builder.appointmentEndDateTime;
        ownerId = builder.ownerId;
        petId = builder.petId;
        description = builder.description;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private LocalDateTime appointmentStartDateTime;
        private LocalDateTime appointmentEndDateTime;
        private UUID id;
        private UUID ownerId;
        private UUID petId;
        private String description;

        private Builder() {
        }

        public Builder appointmentStartDateTime(LocalDateTime val) {
            appointmentStartDateTime = val;
            return this;
        }

        public Builder appointmentEndDateTime(LocalDateTime val) {
            appointmentEndDateTime = val;
            return this;
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

        public Appointment build() {
            return new Appointment(this);
        }
    }
}
