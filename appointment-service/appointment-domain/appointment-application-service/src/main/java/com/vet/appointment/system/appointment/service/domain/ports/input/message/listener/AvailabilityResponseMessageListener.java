package com.vet.appointment.system.appointment.service.domain.ports.input.message.listener;

import com.vet.appointment.system.appointment.service.domain.dto.message.AvailabilityResponse;
import com.vet.appointment.system.messaging.event.AvailabilityAppointmentEventPayload;

public interface AvailabilityResponseMessageListener {

    void appointmentAvailable(AvailabilityResponse availabilityResponse);
    void appointmentUnavailable(AvailabilityResponse availabilityResponse);
}
