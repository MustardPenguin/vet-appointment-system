package com.vet.appointment.system.availability.service.messaging.mapper;

import com.vet.appointment.system.availability.service.domain.dto.message.AvailabilityRequest;
import com.vet.appointment.system.messaging.event.AppointmentAvailabilityEventPayload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AvailabilityMessagingDataMapper {

    public AvailabilityRequest availabilityEventPayloadToAvailabilityRequest(AppointmentAvailabilityEventPayload appointmentAvailabilityEventPayload, UUID sagaId) {
        return AvailabilityRequest.builder()
                .availabilityId(appointmentAvailabilityEventPayload.getAvailabilityId())
                .startDateTime(appointmentAvailabilityEventPayload.getAppointmentStartDateTime())
                .endDateTime(appointmentAvailabilityEventPayload.getAppointmentEndDateTime())
                .reason("Appointment for id: " + appointmentAvailabilityEventPayload.getId())
                .sagaId(sagaId)
                .build();
    }
}
