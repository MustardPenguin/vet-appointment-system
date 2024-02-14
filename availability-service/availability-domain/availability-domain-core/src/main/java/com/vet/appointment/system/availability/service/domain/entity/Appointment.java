package com.vet.appointment.system.availability.service.domain.entity;

import com.vet.appointment.system.domain.entity.AggregateRoot;
import com.vet.appointment.system.domain.valueobject.AppointmentId;

import java.time.LocalDateTime;

public class Appointment extends AggregateRoot<AppointmentId> {
    private final LocalDateTime appointmentStartDateTime;
    private final LocalDateTime appointmentEndDateTime;


    public Appointment(AppointmentId appointmentId, LocalDateTime appointmentStartDateTime, LocalDateTime appointmentEndDateTime) {
        super.setId(appointmentId);
        this.appointmentStartDateTime = appointmentStartDateTime;
        this.appointmentEndDateTime = appointmentEndDateTime;
    }

    public LocalDateTime getAppointmentStartDateTime() {
        return appointmentStartDateTime;
    }

    public LocalDateTime getAppointmentEndDateTime() {
        return appointmentEndDateTime;
    }
}
