package com.vet.appointment.system.appointment.service.messaging.mapper;

import com.vet.appointment.system.appointment.service.domain.dto.message.AvailabilityResponse;
import com.vet.appointment.system.messaging.event.AvailabilityAppointmentEventPayload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppointmentMessagingDataMapper {

    public AvailabilityResponse availabilityAppointmentEventPayloadToAvailabilityResponse(AvailabilityAppointmentEventPayload availabilityAppointmentEventPayload,
                                                                                          UUID sagaId) {
        return AvailabilityResponse.builder()
                .appointmentId(availabilityAppointmentEventPayload.getAppointmentId())
                .sagaId(sagaId)
                .createdAt(availabilityAppointmentEventPayload.getCreatedAt())
                .appointmentStatus(availabilityAppointmentEventPayload.getAppointmentStatus())
                .errorMessages(availabilityAppointmentEventPayload.getErrorMessages())
                .build();
    }
}
