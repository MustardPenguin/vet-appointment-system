package com.vet.appointment.system.availability.service.domain.impl;

import com.vet.appointment.system.availability.service.domain.AvailabilityDomainService;
import com.vet.appointment.system.availability.service.domain.entity.Appointment;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityConfirmedEvent;

public class AvailabilityDomainServiceImpl implements AvailabilityDomainService {


    @Override
    public AvailabilityConfirmedEvent validateAppointmentAvailability(Appointment appointment) {
        return null;
    }
}
