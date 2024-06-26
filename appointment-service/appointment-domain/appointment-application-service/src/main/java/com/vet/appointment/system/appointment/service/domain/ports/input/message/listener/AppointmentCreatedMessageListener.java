package com.vet.appointment.system.appointment.service.domain.ports.input.message.listener;

import com.vet.appointment.system.appointment.service.domain.entity.Appointment;

import java.util.UUID;

public interface AppointmentCreatedMessageListener {

    void appointmentCreated(Appointment appointment, UUID outboxId);
}
