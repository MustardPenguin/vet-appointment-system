package com.vet.appointment.system.availability.service.domain.impl;

import com.vet.appointment.system.availability.service.domain.AvailabilityDomainService;
import com.vet.appointment.system.availability.service.domain.entity.Appointment;
import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityConfirmedEvent;
import com.vet.appointment.system.availability.service.domain.ports.input.message.listener.AppointmentAvailabilityMessageListener;
import com.vet.appointment.system.availability.service.domain.ports.output.repository.AvailabilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AppointmentAvailabilityMessageListenerImpl implements AppointmentAvailabilityMessageListener {

    private final AvailabilityDomainService availabilityDomainService;
    private final AvailabilityRepository availabilityRepository;

    public AppointmentAvailabilityMessageListenerImpl(AvailabilityDomainService availabilityDomainService,
                                                      AvailabilityRepository availabilityRepository) {
        this.availabilityDomainService = availabilityDomainService;
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public void checkAvailability(Appointment appointment) {
        log.info("Checking availability for appointment: {}", appointment.getId().getValue());
        List<Availability> availabilities = availabilityRepository.getAvailabilitiesOnDate(appointment.getAppointmentStartDateTime(), appointment.getAppointmentEndDateTime());
        if(availabilities.isEmpty()) {
            log.info("Availability is found");
        } else {
            log.info("Availability is not found");
        }

        List<String> errorMessages = new ArrayList<>();
//        AvailabilityConfirmedEvent availabilityConfirmedEvent = availabilityDomainService
//                .validateAppointmentAvailability(appointment, availabilities, errorMessages);
    }
}
