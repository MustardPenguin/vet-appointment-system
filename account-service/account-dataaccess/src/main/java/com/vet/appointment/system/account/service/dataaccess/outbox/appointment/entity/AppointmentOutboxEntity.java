package com.vet.appointment.system.account.service.dataaccess.outbox.appointment.entity;

import com.vet.appointment.system.outbox.OutboxStatus;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Table(name = "appointment_outbox", schema = "account")
@Entity
public class AppointmentOutboxEntity {

    @Id
    private UUID id;
    private ZonedDateTime createdAt;
    private ZonedDateTime processedAt;
    private String payload;
    @Enumerated(EnumType.STRING)
    private OutboxStatus outboxStatus;
    @Version
    private int version;

    public AppointmentOutboxEntity() {}

    public UUID getId() {
        return id;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getProcessedAt() {
        return processedAt;
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

    private AppointmentOutboxEntity(Builder builder) {
        id = builder.id;
        createdAt = builder.createdAt;
        processedAt = builder.processedAt;
        payload = builder.payload;
        outboxStatus = builder.outboxStatus;
        version = builder.version;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private UUID id;
        private ZonedDateTime createdAt;
        private ZonedDateTime processedAt;
        private String payload;
        @Enumerated(EnumType.STRING)
        private OutboxStatus outboxStatus;
        private int version;

        private Builder() {}

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder processedAt(ZonedDateTime val) {
            processedAt = val;
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

        public AppointmentOutboxEntity build() {
            return new AppointmentOutboxEntity(this);
        }
    }
}
