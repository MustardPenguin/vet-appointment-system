package com.vet.appointment.system.availability.service.domain.impl;

import com.vet.appointment.system.availability.service.domain.AvailabilityDomainService;
import com.vet.appointment.system.availability.service.domain.entity.Appointment;
import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityAvailableEvent;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityCancelledEvent;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityEvent;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityUnavailableEvent;
import com.vet.appointment.system.availability.service.domain.valueobject.AvailabilityId;
import com.vet.appointment.system.availability.service.domain.valueobject.AvailabilityStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.vet.appointment.system.domain.DomainConstants.UTC;

public class AvailabilityDomainServiceImpl implements AvailabilityDomainService {

    @Override
    public AvailabilityEvent validateAvailability(Availability availability, Optional<List<Availability>> availabilities, List<String> errorMessages) {
        if(!availabilities.get().isEmpty()) {
            errorMessages.add("Availability is already taken, please choose another time! Reason: " + availabilities.get().get(0).getReason());
        }
        if(availability.getStartDateTime().isAfter(availability.getEndDateTime())) {
            errorMessages.add("Start date time cannot be after end date time for request!");
        }

        if(errorMessages.isEmpty()) {
            availability.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
            return new AvailabilityAvailableEvent(availability, ZonedDateTime.now(ZoneId.of(UTC)), errorMessages);
        }
        availability.setAvailabilityStatus(AvailabilityStatus.UNAVAILABLE);
        return new AvailabilityUnavailableEvent(availability, ZonedDateTime.now(ZoneId.of(UTC)), errorMessages);
    }

    @Override
    public AvailabilityEvent cancelAvailability(Availability availability, List<String> errorMessages) {
        if(availability.getAvailabilityStatus() != AvailabilityStatus.AVAILABLE) {
            errorMessages.add("Availability is already cancelled!");
        }
        availability.setAvailabilityStatus(AvailabilityStatus.CANCELLED);
        return new AvailabilityCancelledEvent(availability, ZonedDateTime.now(ZoneId.of(UTC)), errorMessages);
    }
}
