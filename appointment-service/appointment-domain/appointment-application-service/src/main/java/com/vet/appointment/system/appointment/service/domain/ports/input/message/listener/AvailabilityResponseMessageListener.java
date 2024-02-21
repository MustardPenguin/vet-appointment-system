package com.vet.appointment.system.appointment.service.domain.ports.input.message.listener;

import com.vet.appointment.system.messaging.event.AvailabilityAppointmentEventPayload;

public interface AvailabilityResponseMessageListener {

    void appointmentAvailable(AvailabilityAppointmentEventPayload availabilityAppointmentEventPayload);
    void appointmentUnavailable(AvailabilityAppointmentEventPayload availabilityAppointmentEventPayload);
}
