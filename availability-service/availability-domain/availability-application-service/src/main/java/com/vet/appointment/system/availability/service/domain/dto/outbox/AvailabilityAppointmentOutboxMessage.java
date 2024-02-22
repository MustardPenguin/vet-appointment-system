package com.vet.appointment.system.availability.service.domain.dto.outbox;

import com.vet.appointment.system.outbox.OutboxStatus;

import java.time.ZonedDateTime;
import java.util.UUID;

public class AvailabilityAppointmentOutboxMessage {
    private UUID id;
    private ZonedDateTime createdAt;
    private String payload;
    private OutboxStatus outboxStatus;
    private int version;

    private AvailabilityAppointmentOutboxMessage(Builder builder) {
        id = builder.id;
        createdAt = builder.createdAt;
        payload = builder.payload;
        setOutboxStatus(builder.outboxStatus);
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

    public OutboxStatus getOutboxStatus() {
        return outboxStatus;
    }

    public int getVersion() {
        return version;
    }

    public void setOutboxStatus(OutboxStatus outboxStatus) {
        this.outboxStatus = outboxStatus;
    }

    public static final class Builder {
        private UUID id;
        private ZonedDateTime createdAt;
        private String payload;
        private OutboxStatus outboxStatus;
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

        public Builder outboxStatus(OutboxStatus val) {
            outboxStatus = val;
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
