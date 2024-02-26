package com.vet.appointment.system.appointment.service.domain.dto.outbox;

import com.vet.appointment.system.saga.SagaStatus;

import java.time.ZonedDateTime;
import java.util.UUID;

public class AppointmentAvailabilityOutboxMessage {
    private UUID id;
    private ZonedDateTime createdAt;
    private String payload;
    private int version;
    private SagaStatus sagaStatus;
    private UUID sagaId;
    private String sagaType;

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

    public SagaStatus getSagaStatus() {
        return sagaStatus;
    }

    public UUID getSagaId() {
        return sagaId;
    }

    public String getSagaType() {
        return sagaType;
    }

    private AppointmentAvailabilityOutboxMessage(Builder builder) {
        id = builder.id;
        createdAt = builder.createdAt;
        payload = builder.payload;
        sagaId = builder.sagaId;
        sagaType = builder.sagaType;
        setSagaStatus(builder.sagaStatus);
        version = builder.version;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void setSagaStatus(SagaStatus sagaStatus) {
        this.sagaStatus = sagaStatus;
    }

    public static final class Builder {
        private UUID id;
        private ZonedDateTime createdAt;
        private String payload;
        private int version;
        private UUID sagaId;
        private SagaStatus sagaStatus;
        private String sagaType;

        private Builder() {
        }

        public Builder sagaId(UUID val) {
            sagaId = val;
            return this;
        }

        public Builder sagaStatus(SagaStatus val) {
            sagaStatus = val;
            return this;
        }

        public Builder sagaType(String val) {
            sagaType = val;
            return this;
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

        public AppointmentAvailabilityOutboxMessage build() {
            return new AppointmentAvailabilityOutboxMessage(this);
        }
    }
}
