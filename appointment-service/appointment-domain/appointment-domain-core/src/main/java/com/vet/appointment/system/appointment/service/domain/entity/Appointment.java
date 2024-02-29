package com.vet.appointment.system.appointment.service.domain.entity;

import com.vet.appointment.system.appointment.service.domain.exception.AppointmentDomainException;
import com.vet.appointment.system.domain.entity.AggregateRoot;
import com.vet.appointment.system.domain.valueobject.AppointmentId;
import com.vet.appointment.system.domain.valueobject.AppointmentStatus;
import com.vet.appointment.system.domain.valueobject.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment extends AggregateRoot<AppointmentId> {

    private final LocalDateTime appointmentStartDateTime;
    private final LocalDateTime appointmentEndDateTime;
    private final UUID ownerId;
    private final UUID petId;
    private final String description;
    private AppointmentStatus appointmentStatus;
    private PaymentStatus paymentStatus;
    private String errorMessages;
    private UUID availabilityId;
    private UUID paymentId;
    private BigDecimal cost;

    public LocalDateTime getAppointmentStartDateTime() {
        return appointmentStartDateTime;
    }

    public LocalDateTime getAppointmentEndDateTime() {
        return appointmentEndDateTime;
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

    public void setAvailabilityId(UUID availabilityId) {
        this.availabilityId = availabilityId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void initAvailability() {
        if(appointmentStatus != AppointmentStatus.REQUESTING) {
            throw new AppointmentDomainException("Appointment is not in correct state for availability operation!");
        }
        if(availabilityId == null) {
            throw new AppointmentDomainException("Availability id is not set for appointment!");
        }
        int duration = appointmentEndDateTime.getMinute() - appointmentStartDateTime.getMinute();
        if(duration < 0) {
            throw new AppointmentDomainException("Appointment duration is negative!");
        }
        cost = new BigDecimal(duration).multiply(new BigDecimal(.25));
        appointmentStatus = AppointmentStatus.AVAILABLE;
    }

    public void initUnavailability(String errorMessages) {
        if(appointmentStatus != AppointmentStatus.REQUESTING && appointmentStatus != AppointmentStatus.CANCELLING) {
            throw new AppointmentDomainException("Appointment is not in correct state for unavailability operation!");
        }
        appointmentStatus = AppointmentStatus.UNAVAILABLE;
        this.errorMessages = errorMessages;
    }

    public void initCancelling() {
        appointmentStatus = AppointmentStatus.CANCELLING;
    }

    private Appointment(Builder builder) {
        super.setId(new AppointmentId(builder.id));
        appointmentStartDateTime = builder.appointmentStartDateTime;
        appointmentEndDateTime = builder.appointmentEndDateTime;
        ownerId = builder.ownerId;
        petId = builder.petId;
        description = builder.description;
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


    public static final class Builder {
        private LocalDateTime appointmentStartDateTime;
        private LocalDateTime appointmentEndDateTime;
        private UUID id;
        private UUID ownerId;
        private UUID petId;
        private String description;
        private AppointmentStatus appointmentStatus;
        private PaymentStatus paymentStatus;
        private String errorMessages;
        private UUID availabilityId;
        private UUID paymentId;
        private BigDecimal cost;

        private Builder() {
        }

        public Builder appointmentStartDateTime(LocalDateTime val) {
            appointmentStartDateTime = val;
            return this;
        }

        public Builder appointmentEndDateTime(LocalDateTime val) {
            appointmentEndDateTime = val;
            return this;
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

        public Appointment build() {
            return new Appointment(this);
        }
    }
}
