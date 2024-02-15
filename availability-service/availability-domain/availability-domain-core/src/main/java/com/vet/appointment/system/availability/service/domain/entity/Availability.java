package com.vet.appointment.system.availability.service.domain.entity;

import com.vet.appointment.system.availability.service.domain.valueobject.AvailabilityId;
import com.vet.appointment.system.domain.entity.AggregateRoot;

import java.time.LocalDateTime;
import java.util.UUID;

public class Availability extends AggregateRoot<AvailabilityId> {

    private final UUID appointmentId;
    private final LocalDateTime appointmentStartDateTime;
    private final LocalDateTime appointmentEndDateTime;
    private final String reason;

    private Availability(Builder builder) {
        super.setId(new AvailabilityId(builder.id));
        appointmentId = builder.appointmentId;
        appointmentStartDateTime = builder.appointmentStartDateTime;
        appointmentEndDateTime = builder.appointmentEndDateTime;
        reason = builder.reason;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getAppointmentId() {
        return appointmentId;
    }

    public LocalDateTime getAppointmentStartDateTime() {
        return appointmentStartDateTime;
    }

    public LocalDateTime getAppointmentEndDateTime() {
        return appointmentEndDateTime;
    }

    public String getReason() {
        return reason;
    }

    public static final class Builder {
        private UUID id;
        private UUID appointmentId;
        private LocalDateTime appointmentStartDateTime;
        private LocalDateTime appointmentEndDateTime;
        private String reason;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder appointmentId(UUID val) {
            appointmentId = val;
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

        public Builder reason(String val) {
            reason = val;
            return this;
        }

        public Availability build() {
            return new Availability(this);
        }
    }
}
