package com.vet.appointment.system.appointment.service.domain.impl.message.listener;

import com.vet.appointment.system.appointment.service.domain.dto.message.AvailabilityResponse;
import com.vet.appointment.system.appointment.service.domain.ports.input.message.listener.AvailabilityResponseMessageListener;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.AppointmentRepository;
import com.vet.appointment.system.appointment.service.domain.saga.AppointmentAvailabilitySaga;
import com.vet.appointment.system.messaging.event.AvailabilityAppointmentEventPayload;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityResponseMessageListenerImpl implements AvailabilityResponseMessageListener {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentAvailabilitySaga appointmentAvailabilitySaga;


    public AvailabilityResponseMessageListenerImpl(AppointmentRepository appointmentRepository,
                                                   AppointmentAvailabilitySaga appointmentAvailabilitySaga) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentAvailabilitySaga = appointmentAvailabilitySaga;
    }

    @Override
    public void appointmentAvailable(AvailabilityResponse availabilityResponse) {
        appointmentAvailabilitySaga.process(availabilityResponse);
    }

    @Override
    public void appointmentUnavailable(AvailabilityResponse availabilityResponse) {
        appointmentAvailabilitySaga.rollback(availabilityResponse);
    }
}
