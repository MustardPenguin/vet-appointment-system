package com.vet.appointment.system.availability.service.domain.dto.outbox;

import java.time.ZonedDateTime;
import java.util.UUID;

public class AvailabilityAppointmentOutboxMessage {
    private UUID id;
    private UUID sagaId;
    private String sagaType;
    private ZonedDateTime createdAt;
    private String payload;
    private int version;

    private AvailabilityAppointmentOutboxMessage(Builder builder) {
        id = builder.id;
        sagaId = builder.sagaId;
        sagaType = builder.sagaType;
        createdAt = builder.createdAt;
        payload = builder.payload;
        version = builder.version;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getId() {
        return id;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public UUID getSagaId() {
        return sagaId;
    }

    public String getPayload() {
        return payload;
    }

    public int getVersion() {
        return version;
    }

    public String getSagaType() {
        return sagaType;
    }


    public static final class Builder {
        private UUID id;
        private UUID sagaId;
        private String sagaType;
        private ZonedDateTime createdAt;
        private String payload;
        private int version;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder sagaId(UUID val) {
            sagaId = val;
            return this;
        }

        public Builder sagaType(String val) {
            sagaType = val;
            return this;
        }

        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder payload(String val) {
            payload = val;
            return this;
        }

        public Builder version(int val) {
            version = val;
            return this;
        }

        public AvailabilityAppointmentOutboxMessage build() {
            return new AvailabilityAppointmentOutboxMessage(this);
        }
    }
}
