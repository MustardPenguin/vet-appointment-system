package com.vet.appointment.system.availability.service.domain.impl;

import com.vet.appointment.system.availability.service.domain.AvailabilityDomainService;
import com.vet.appointment.system.availability.service.domain.entity.Appointment;
import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityConfirmedEvent;
import com.vet.appointment.system.availability.service.domain.exception.AvailabilityDomainException;
import com.vet.appointment.system.availability.service.domain.valueobject.EventType;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.vet.appointment.system.domain.DomainConstants.UTC;

public class AvailabilityDomainServiceImpl implements AvailabilityDomainService {

    @Override
    public AvailabilityConfirmedEvent validateAppointmentAvailability(Appointment appointment, Optional<Availability> optionalAvailability, List<String> errorMessages) {
        if(optionalAvailability.isPresent()) {
            errorMessages.add("Availability is already taken! Reason: " + optionalAvailability.get().getReason());
        }
        Availability availability = Availability.builder()
                .id(UUID.randomUUID())
                .eventType(EventType.APPOINTMENT)
                .startDateTime(appointment.getAppointmentStartDateTime())
                .endDateTime(appointment.getAppointmentEndDateTime())
                .reason("Vet appointment scheduled")
                .build();

        return new AvailabilityConfirmedEvent(availability, ZonedDateTime.now(ZoneId.of(UTC)), errorMessages);
    }
}
