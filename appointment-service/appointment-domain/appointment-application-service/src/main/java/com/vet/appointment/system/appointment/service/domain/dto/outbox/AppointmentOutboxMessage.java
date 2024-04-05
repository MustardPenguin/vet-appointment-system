package com.vet.appointment.system.appointment.service.domain.dto.outbox;

import java.time.ZonedDateTime;
import java.util.UUID;

public class AppointmentOutboxMessage {

    private UUID id;
    private ZonedDateTime createdAt;
    private String payload;
    private int version;

    private AppointmentOutboxMessage(Builder builder) {
        id = builder.id;
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

    public String getPayload() {
        return payload;
    }

    public int getVersion() {
        return version;
    }


    public static final class Builder {
        private UUID id;
        private ZonedDateTime createdAt;
        private String payload;
        private int version;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
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

        public AppointmentOutboxMessage build() {
            return new AppointmentOutboxMessage(this);
        }
    }
}
