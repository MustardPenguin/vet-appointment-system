package com.vet.appointment.system.payment.service.dataaccess.outbox.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_outbox")
public class PaymentOutboxEntity {

    @Id
    private UUID id;
    private ZonedDateTime createdAt;
    private String payload;
    @Version
    private int version;

    public PaymentOutboxEntity() {}

    private PaymentOutboxEntity(Builder builder) {
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

        public PaymentOutboxEntity build() {
            return new PaymentOutboxEntity(this);
        }
    }
}
