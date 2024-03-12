package com.vet.appointment.system.availability.service.domain.dto.message;

import java.time.LocalDateTime;
import java.util.UUID;

public class AvailabilityRequest {

    private UUID availabilityId;
    private UUID sagaId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String reason;

    private AvailabilityRequest(Builder builder) {
        availabilityId = builder.availabilityId;
        sagaId = builder.sagaId;
        startDateTime = builder.startDateTime;
        endDateTime = builder.endDateTime;
        reason = builder.reason;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getAvailabilityId() {
        return availabilityId;
    }

    public UUID getSagaId() {
        return sagaId;
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
        private UUID availabilityId;
        private UUID sagaId;
        private LocalDateTime startDateTime;
        private LocalDateTime endDateTime;
        private String reason;

        private Builder() {
        }

        public Builder availabilityId(UUID val) {
            availabilityId = val;
            return this;
        }

        public Builder sagaId(UUID val) {
            sagaId = val;
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

        public AvailabilityRequest build() {
            return new AvailabilityRequest(this);
        }
    }
}
