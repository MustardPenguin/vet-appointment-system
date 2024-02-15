package com.vet.appointment.system.availability.service.domain.impl;

import com.vet.appointment.system.availability.service.domain.AvailabilityDomainService;
import com.vet.appointment.system.availability.service.domain.entity.Appointment;
import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityConfirmedEvent;

import java.util.List;

public class AvailabilityDomainServiceImpl implements AvailabilityDomainService {

    @Override
    public AvailabilityConfirmedEvent validateAppointmentAvailability(Appointment appointment, List<Availability> availabilities, List<String> errorMessages) {
        availabilities.forEach(availability -> {
            System.out.println(availability.getAppointmentStartDateTime());
        });

        return null;
    }
}
