package com.vet.appointment.system.appointment.service.domain.impl;

import com.vet.appointment.system.appointment.service.domain.AppointmentDomainService;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.entity.Pet;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCreatedEvent;
import com.vet.appointment.system.appointment.service.domain.exception.AppointmentDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.vet.appointment.system.domain.DomainConstants.UTC;

public class AppointmentDomainServiceImpl implements AppointmentDomainService {

    @Override
    public AppointmentCreatedEvent validateAndInitiateAppointment(Appointment appointment, Pet pet, List<String> errorMessages) {
        if(appointment.getOwnerId() != pet.getOwnerId()) {
            throw new AppointmentDomainException("Owner id of pet and appointment does not match!");
        }
        if(appointment.getAppointmentStartDateTime().isAfter(appointment.getAppointmentEndDateTime())) {
            throw new AppointmentDomainException("Appointment start date time is after appointment end date time!");
        }

        return new AppointmentCreatedEvent(appointment, ZonedDateTime.now(ZoneId.of(UTC)));
    }
}
