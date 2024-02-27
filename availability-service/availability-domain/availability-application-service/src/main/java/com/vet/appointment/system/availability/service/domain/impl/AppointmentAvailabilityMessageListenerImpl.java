package com.vet.appointment.system.availability.service.domain.impl;

import com.vet.appointment.system.availability.service.domain.AvailabilityDomainService;
import com.vet.appointment.system.availability.service.domain.dto.message.AvailabilityRequest;
import com.vet.appointment.system.availability.service.domain.entity.Appointment;
import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityConfirmedEvent;
import com.vet.appointment.system.availability.service.domain.exception.AvailabilityDomainException;
import com.vet.appointment.system.availability.service.domain.helper.AppointmentOutboxHelper;
import com.vet.appointment.system.availability.service.domain.ports.input.message.listener.AppointmentAvailabilityMessageListener;
import com.vet.appointment.system.availability.service.domain.ports.output.repository.AvailabilityRepository;
import com.vet.appointment.system.domain.valueobject.AppointmentId;
import com.vet.appointment.system.domain.valueobject.AppointmentStatus;
import com.vet.appointment.system.messaging.event.AvailabilityAppointmentEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class AppointmentAvailabilityMessageListenerImpl implements AppointmentAvailabilityMessageListener {

    private final AvailabilityDomainService availabilityDomainService;
    private final AvailabilityRepository availabilityRepository;
    private final AppointmentOutboxHelper appointmentOutboxHelper;

    public AppointmentAvailabilityMessageListenerImpl(AvailabilityDomainService availabilityDomainService,
                                                      AvailabilityRepository availabilityRepository,
                                                      AppointmentOutboxHelper appointmentOutboxHelper) {
        this.availabilityDomainService = availabilityDomainService;
        this.availabilityRepository = availabilityRepository;
        this.appointmentOutboxHelper = appointmentOutboxHelper;
    }

    // TODO This method should be generalized, not specific to appointment
    @Override
    @Transactional
    public void checkAvailability(AvailabilityRequest availabilityRequest) {
        Appointment appointment = new Appointment(
                new AppointmentId(availabilityRequest.getEventId()),
                availabilityRequest.getStartDateTime(),
                availabilityRequest.getEndDateTime());

        log.info("Checking availability for appointment: {}", appointment.getId().getValue());
        Optional<List<Availability>> optionalAvailabilities = availabilityRepository.getAvailabilitiesOnDate(appointment.getAppointmentStartDateTime(), appointment.getAppointmentEndDateTime());

        List<String> errorMessages = new ArrayList<>();
        AvailabilityConfirmedEvent availabilityConfirmedEvent = availabilityDomainService
                .validateAppointmentAvailability(appointment, optionalAvailabilities, errorMessages);

        AppointmentStatus appointmentStatus = optionalAvailabilities.get().isEmpty() ? AppointmentStatus.AVAILABLE : AppointmentStatus.UNAVAILABLE;
        appointmentOutboxHelper.saveAppointmentOutboxMessage(new AvailabilityAppointmentEventPayload(
                        availabilityConfirmedEvent.getEntity().getEventId(),
                        String.join(", ", errorMessages),
                        availabilityConfirmedEvent.getCreatedAt(),
                        appointmentStatus),
                availabilityRequest.getSagaId());

        log.info("Appointment status: {}", appointmentStatus);
        if(appointmentStatus.equals(AppointmentStatus.UNAVAILABLE)) {
            log.info("Appointment is not available for appointment id: {}, Reasons: {}", appointment.getId().getValue(), errorMessages);
            return;
        }
        log.info("Availability for appointment id {} is confirmed, saving to database as id {}",
                appointment.getId().getValue(), availabilityConfirmedEvent.getEntity().getEventId());
        Availability response = availabilityRepository.save(availabilityConfirmedEvent.getEntity());
        if(response == null) {
            log.error("Failed to save availability entity for appointment id: {}", availabilityConfirmedEvent.getEntity().getEventId());
            throw new AvailabilityDomainException("Failed to save availability entity for appointment id: " +
                    availabilityConfirmedEvent.getEntity().getEventId());
        }
    }
}
