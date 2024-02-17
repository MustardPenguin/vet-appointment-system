package com.vet.appointment.system.availability.service.dataaccess.availability.entity;

import com.vet.appointment.system.availability.service.domain.valueobject.EventType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "availabilities")
public class AvailabilityEntity {

    @Id
    private UUID id;
    private UUID eventId;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String reason;

    public AvailabilityEntity() {}

    private AvailabilityEntity(Builder builder) {
        id = builder.id;
        eventId = builder.eventId;
        eventType = builder.eventType;
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

    public UUID getEventId() {
        return eventId;
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

    public EventType getEventType() {
        return eventType;
    }


    public static final class Builder {
        private UUID id;
        private UUID eventId;
        private EventType eventType;
        private LocalDateTime startDateTime;
        private LocalDateTime endDateTime;
        private String reason;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder eventId(UUID val) {
            eventId = val;
            return this;
        }

        public Builder eventType(EventType val) {
            eventType = val;
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
