package com.vet.appointment.system.appointment.service.dataaccess.appointment.entity;

import com.vet.appointment.system.domain.valueobject.AppointmentStatus;
import com.vet.appointment.system.domain.valueobject.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointments")
public class AppointmentEntity {

    @Id
    private UUID id;
    private UUID ownerId;
    private UUID petId;
    private String description;
    private LocalDateTime appointmentStartDateTime;
    private LocalDateTime appointmentEndDateTime;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private UUID availabilityId;
    private UUID paymentId;
    private BigDecimal cost;
    private String errorMessages;

    public AppointmentEntity() {}

    private AppointmentEntity(Builder builder) {
        id = builder.id;
        ownerId = builder.ownerId;
        petId = builder.petId;
        description = builder.description;
        appointmentStartDateTime = builder.appointmentStartDateTime;
        appointmentEndDateTime = builder.appointmentEndDateTime;
        appointmentStatus = builder.appointmentStatus;
        paymentStatus = builder.paymentStatus;
        errorMessages = builder.errorMessages;
        availabilityId = builder.availabilityId;
        paymentId = builder.paymentId;
        cost = builder.cost;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getId() {
        return id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public UUID getPetId() {
        return petId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getAppointmentStartDateTime() {
        return appointmentStartDateTime;
    }

    public LocalDateTime getAppointmentEndDateTime() {
        return appointmentEndDateTime;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public String getErrorMessages() {
        return errorMessages;
    }

    public UUID getAvailabilityId() {
        return availabilityId;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public static final class Builder {
        private UUID id;
        private UUID ownerId;
        private UUID petId;
        private String description;
        private LocalDateTime appointmentStartDateTime;
        private LocalDateTime appointmentEndDateTime;
        private AppointmentStatus appointmentStatus;
        private PaymentStatus paymentStatus;
        private String errorMessages;
        private UUID availabilityId;
        private UUID paymentId;
        private BigDecimal cost;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder ownerId(UUID val) {
            ownerId = val;
            return this;
        }

        public Builder petId(UUID val) {
            petId = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder appointmentStartDateTime(LocalDateTime val) {
            appointmentStartDateTime = val;
            return this;
        }

        public Builder appointmentEndDateTime(LocalDateTime val) {
            appointmentEndDateTime = val;
            return this;
        }

        public Builder appointmentStatus(AppointmentStatus val) {
            appointmentStatus = val;
            return this;
        }

        public Builder paymentStatus(PaymentStatus val) {
            paymentStatus = val;
            return this;
        }

        public Builder errorMessages(String val) {
            errorMessages = val;
            return this;
        }

        public Builder availabilityId(UUID val) {
            availabilityId = val;
            return this;
        }

        public Builder paymentId(UUID val) {
            paymentId = val;
            return this;
        }

        public Builder cost(BigDecimal val) {
            cost = val;
            return this;
        }

        public AppointmentEntity build() {
            return new AppointmentEntity(this);
        }
    }
}
