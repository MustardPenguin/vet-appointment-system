package com.vet.appointment.system.availability.service.domain.entity;

import com.vet.appointment.system.availability.service.domain.valueobject.AvailabilityId;
import com.vet.appointment.system.domain.entity.AggregateRoot;

import java.time.LocalDateTime;
import java.util.UUID;

public class Availability extends AggregateRoot<AvailabilityId> {

    private final UUID eventId;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final String reason;

    private Availability(Builder builder) {
        super.setId(new AvailabilityId(builder.id));
        eventId = builder.appointmentId;
        startDateTime = builder.startDateTime;
        endDateTime = builder.endDateTime;
        reason = builder.reason;
    }

    public static Builder builder() {
        return new Builder();
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

    public static final class Builder {
        private UUID id;
        private UUID eventId;
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

        public Availability build() {
            return new Availability(this);
        }
    }
}
