package com.vet.appointment.system.appointment.service.dataaccess.outbox.payment.entity;

import com.vet.appointment.system.saga.SagaStatus;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_outbox")
public class PaymentOutboxEntity {

    @Id
    private UUID id;
    private UUID sagaId;
    private String sagaType;
    private ZonedDateTime createdAt;
    private String payload;
    @Enumerated(EnumType.STRING)
    private SagaStatus sagaStatus;
    @Version
    private int version;

    public PaymentOutboxEntity() {}

    private PaymentOutboxEntity(Builder builder) {
        id = builder.id;
        sagaId = builder.sagaId;
        sagaType = builder.sagaType;
        createdAt = builder.createdAt;
        payload = builder.payload;
        setSagaStatus(builder.sagaStatus);
        version = builder.version;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getId() {
        return id;
    }

    public UUID getSagaId() {
        return sagaId;
    }

    public String getSagaType() {
        return sagaType;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public String getPayload() {
        return payload;
    }

    public SagaStatus getSagaStatus() {
        return sagaStatus;
    }

    public int getVersion() {
        return version;
    }

    public void setSagaStatus(SagaStatus sagaStatus) {
        this.sagaStatus = sagaStatus;
    }


    public static final class Builder {
        private UUID id;
        private UUID sagaId;
        private String sagaType;
        private ZonedDateTime createdAt;
        private String payload;
        private SagaStatus sagaStatus;
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

        public Builder sagaStatus(SagaStatus val) {
            sagaStatus = val;
            return this;
        }

        public Builder version(int val) {
            version = val;
            return this;
        }

        public PaymentOutboxEntity build() {
            return new PaymentOutboxEntity(this);
        }
    }
}
