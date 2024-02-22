package com.vet.appointment.system.appointment.service.domain.dto.outbox;

import com.vet.appointment.system.outbox.OutboxStatus;
import com.vet.appointment.system.saga.SagaStatus;

import java.time.ZonedDateTime;
import java.util.UUID;

public class AppointmentAvailabilityOutboxMessage {
    private UUID id;
    private ZonedDateTime createdAt;
    private String payload;
    private OutboxStatus outboxStatus;
    private int version;
    private SagaStatus sagaStatus;
    private UUID sagaId;

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

    public SagaStatus getSagaStatus() {
        return sagaStatus;
    }

    public UUID getSagaId() {
        return sagaId;
    }

    private AppointmentAvailabilityOutboxMessage(Builder builder) {
        id = builder.id;
        createdAt = builder.createdAt;
        payload = builder.payload;
        sagaId = builder.sagaId;
        setSagaStatus(builder.sagaStatus);
        setOutboxStatus(builder.outboxStatus);
        version = builder.version;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void setOutboxStatus(OutboxStatus outboxStatus) {
        this.outboxStatus = outboxStatus;
    }

    public void setSagaStatus(SagaStatus sagaStatus) {
        this.sagaStatus = sagaStatus;
    }

    public static final class Builder {
        private UUID id;
        private ZonedDateTime createdAt;
        private String payload;
        private OutboxStatus outboxStatus;
        private int version;
        private UUID sagaId;
        private SagaStatus sagaStatus;

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

        public AppointmentAvailabilityOutboxMessage build() {
            return new AppointmentAvailabilityOutboxMessage(this);
        }
    }
}
