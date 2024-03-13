package com.vet.appointment.system.availability.service.domain.impl;

import com.vet.appointment.system.availability.service.domain.AvailabilityDomainService;
import com.vet.appointment.system.availability.service.domain.dto.message.AvailabilityRequest;
import com.vet.appointment.system.availability.service.domain.entity.Appointment;
import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityAvailableEvent;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityEvent;
import com.vet.appointment.system.availability.service.domain.exception.AvailabilityDomainException;
import com.vet.appointment.system.availability.service.domain.helper.AppointmentOutboxHelper;
import com.vet.appointment.system.availability.service.domain.helper.AvailabilityServiceHelper;
import com.vet.appointment.system.availability.service.domain.mapper.AvailabilityDataMapper;
import com.vet.appointment.system.availability.service.domain.ports.input.message.listener.AppointmentAvailabilityMessageListener;
import com.vet.appointment.system.availability.service.domain.ports.output.repository.AvailabilityRepository;
import com.vet.appointment.system.availability.service.domain.valueobject.AvailabilityId;
import com.vet.appointment.system.availability.service.domain.valueobject.AvailabilityStatus;
import com.vet.appointment.system.domain.valueobject.AppointmentId;
import com.vet.appointment.system.domain.valueobject.AppointmentStatus;
import com.vet.appointment.system.messaging.event.AvailabilityAppointmentEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class AppointmentAvailabilityMessageListenerImpl implements AppointmentAvailabilityMessageListener {

    private final AvailabilityDataMapper availabilityDataMapper;
    private final AvailabilityRepository availabilityRepository;
    private final AppointmentOutboxHelper appointmentOutboxHelper;
    private final AvailabilityServiceHelper availabilityServiceHelper;

    public AppointmentAvailabilityMessageListenerImpl(AvailabilityDataMapper availabilityDataMapper,
                                                      AvailabilityRepository availabilityRepository,
                                                      AppointmentOutboxHelper appointmentOutboxHelper,
                                                      AvailabilityServiceHelper availabilityServiceHelper) {
        this.availabilityDataMapper = availabilityDataMapper;
        this.availabilityRepository = availabilityRepository;
        this.appointmentOutboxHelper = appointmentOutboxHelper;
        this.availabilityServiceHelper = availabilityServiceHelper;
    }

    @Override
    @Transactional
    public void checkAvailability(AvailabilityRequest availabilityRequest, UUID appointmentId) {
        AvailabilityEvent availabilityEvent = availabilityServiceHelper.checkAvailability(availabilityRequest);

        AvailabilityStatus availabilityStatus = availabilityEvent.getEntity().getAvailabilityStatus();
        AppointmentStatus appointmentStatus = availabilityStatus == AvailabilityStatus.AVAILABLE ? AppointmentStatus.AVAILABLE : AppointmentStatus.UNAVAILABLE;
        log.info("Availability status: {} for appointment id: {}", availabilityStatus, appointmentId);

        availabilityEvent.getEntity().setId(new AvailabilityId(UUID.randomUUID()));
        appointmentOutboxHelper.saveAppointmentOutboxMessage(
                availabilityDataMapper.availiblityEventToAvailabilityAppointmentEventPayload(
                        availabilityEvent, appointmentId, appointmentStatus),
                availabilityRequest.getSagaId());

        if(availabilityStatus == AvailabilityStatus.UNAVAILABLE) {
            log.info("Appointment is not available for appointment id: {}, Reasons: {}", appointmentId, availabilityEvent.getErrorMessages());
            return;
        }

        save(availabilityEvent.getEntity(), appointmentId);
    }

    @Override
    @Transactional
    public void cancelAvailability(AvailabilityRequest availabilityRequest, UUID appointmentId) {
        UUID availabilityId = availabilityRequest.getAvailabilityId();
        Optional<Availability> availabilityOptional = availabilityRepository.findAvailabilityById(availabilityId);
        if(availabilityOptional.isEmpty()) {
            log.error("Availability not found for availability id: {}", availabilityId);
            throw new AvailabilityDomainException("Availability not found for availability id: " + availabilityId);
        }
        Availability availability = availabilityOptional.get();
        log.info("Cancelling availability for availability id: {}", availabilityId);
        availabilityRepository.delete(availability);

        AvailabilityEvent availabilityEvent = availabilityServiceHelper.cancelAvailability(availabilityRequest);

        appointmentOutboxHelper.saveAppointmentOutboxMessage(
                availabilityDataMapper.availiblityEventToAvailabilityAppointmentEventPayload(
                        availabilityEvent, appointmentId, AppointmentStatus.CANCELLED),
                availabilityRequest.getSagaId());
    }

    private Availability save(Availability availability, UUID appointmentId) {
        Availability response = availabilityRepository.save(availability);
        if(response == null) {
            log.error("Failed to save availability entity for appointment id: {}", appointmentId);
            throw new AvailabilityDomainException("Failed to save availability entity for appointment id: " +
                    appointmentId);
        }
        return response;
    }
}
