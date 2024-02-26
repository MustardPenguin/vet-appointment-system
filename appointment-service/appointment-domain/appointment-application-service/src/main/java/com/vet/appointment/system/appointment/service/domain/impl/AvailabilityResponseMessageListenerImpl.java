package com.vet.appointment.system.appointment.service.domain.impl;

import com.vet.appointment.system.appointment.service.domain.dto.message.AvailabilityResponse;
import com.vet.appointment.system.appointment.service.domain.ports.input.message.listener.AvailabilityResponseMessageListener;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.AppointmentRepository;
import com.vet.appointment.system.messaging.event.AvailabilityAppointmentEventPayload;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityResponseMessageListenerImpl implements AvailabilityResponseMessageListener {

    private final AppointmentRepository appointmentRepository;

    public AvailabilityResponseMessageListenerImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void appointmentAvailable(AvailabilityResponse availabilityResponse) {

    }

    @Override
    public void appointmentUnavailable(AvailabilityResponse availabilityResponse) {

    }
}
