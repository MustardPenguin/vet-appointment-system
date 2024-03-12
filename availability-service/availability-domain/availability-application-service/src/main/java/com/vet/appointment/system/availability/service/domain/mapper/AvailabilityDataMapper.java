package com.vet.appointment.system.availability.service.domain.mapper;

import com.vet.appointment.system.availability.service.domain.dto.message.AvailabilityRequest;
import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityEvent;
import com.vet.appointment.system.domain.valueobject.AppointmentStatus;
import com.vet.appointment.system.messaging.event.AvailabilityAppointmentEventPayload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AvailabilityDataMapper {

    public AvailabilityAppointmentEventPayload availiblityEventToAvailabilityAppointmentEventPayload(AvailabilityEvent availabilityEvent,
                                                                                                     UUID appointmentId,
                                                                                                     AppointmentStatus appointmentStatus) {
        return AvailabilityAppointmentEventPayload.builder()
                .appointmentId(appointmentId)
                .availabilityId(availabilityEvent.getEntity().getId().getValue().toString())
                .appointmentStatus(appointmentStatus)
                .createdAt(availabilityEvent.getCreatedAt())
                .errorMessages(String.join(", ", availabilityEvent.getErrorMessages()))
                .build();
    }

    public Availability availabilityRequestToAvailability(AvailabilityRequest availabilityRequest) {
        return Availability.builder()
                .id(availabilityRequest.getAvailabilityId())
                .startDateTime(availabilityRequest.getStartDateTime())
                .endDateTime(availabilityRequest.getEndDateTime())
                .reason(availabilityRequest.getReason())
                .build();
    }
}
