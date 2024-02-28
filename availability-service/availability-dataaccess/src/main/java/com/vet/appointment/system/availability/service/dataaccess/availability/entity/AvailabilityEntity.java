package com.vet.appointment.system.availability.service.dataaccess.availability.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "availabilities")
public class AvailabilityEntity {

    @Id
    private UUID id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String reason;

    public AvailabilityEntity() {}

    private AvailabilityEntity(Builder builder) {
        id = builder.id;
        startDateTime = builder.startDateTime;
        endDateTime = builder.endDateTime;
        reason = builder.reason;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public String getReason() {
        return reason;
    }


    public static final class Builder {
        private UUID id;
        private LocalDateTime startDateTime;
        private LocalDateTime endDateTime;
        private String reason;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder startDateTime(LocalDateTime val) {
            startDateTime = val;
            return this;
        }

        public Builder endDateTime(LocalDateTime val) {
            endDateTime = val;
            return this;
        }

        public Builder reason(String val) {
            reason = val;
            return this;
        }

        public AvailabilityEntity build() {
            return new AvailabilityEntity(this);
        }
    }
}
