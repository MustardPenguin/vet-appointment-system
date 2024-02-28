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

        appointmentOutboxHelper.saveAppointmentOutboxMessage(
                availabilityDataMapper.availiblityEventToAvailabilityAppointmentEventPayload(
                        availabilityEvent, appointmentId, appointmentStatus),
                availabilityRequest.getSagaId());

        if(availabilityStatus == AvailabilityStatus.UNAVAILABLE) {
            log.info("Appointment is not available for appointment id: {}, Reasons: {}", appointmentId, availabilityEvent.getErrorMessages());
            return;
        }

        Availability response = availabilityRepository.save(availabilityEvent.getEntity());
        if(response == null) {
            log.error("Failed to save availability entity for appointment id: {}", appointmentId);
            throw new AvailabilityDomainException("Failed to save availability entity for appointment id: " +
                    appointmentId);
        }
    }

//    @Override
//    @Transactional
//    public void checkAvailability(AvailabilityRequest availabilityRequest) {
//        Appointment appointment = new Appointment(
//                new AppointmentId(availabilityRequest.getEventId()),
//                availabilityRequest.getStartDateTime(),
//                availabilityRequest.getEndDateTime());
//
//        log.info("Checking availability for appointment: {}", appointment.getId().getValue());
//        Optional<List<Availability>> optionalAvailabilities = availabilityRepository.getAvailabilitiesOnDate(appointment.getAppointmentStartDateTime(), appointment.getAppointmentEndDateTime());
//
//        List<String> errorMessages = new ArrayList<>();
//        AvailabilityEvent availabilityEvent = availabilityDomainService
//                .validateAppointmentAvailability(appointment, optionalAvailabilities, errorMessages);
//
//        AppointmentStatus appointmentStatus = optionalAvailabilities.get().isEmpty() ? AppointmentStatus.AVAILABLE : AppointmentStatus.UNAVAILABLE;
//        appointmentOutboxHelper.saveAppointmentOutboxMessage(new AvailabilityAppointmentEventPayload(
//                        availabilityEvent.getEntity().getEventId(),
//                        String.join(", ", errorMessages),
//                        availabilityEvent.getCreatedAt(),
//                        appointmentStatus),
//                availabilityRequest.getSagaId());
//
//        log.info("Appointment status: {}", appointmentStatus);
//        if(appointmentStatus.equals(AppointmentStatus.UNAVAILABLE)) {
//            log.info("Appointment is not available for appointment id: {}, Reasons: {}", appointment.getId().getValue(), errorMessages);
//            return;
//        }
//        log.info("Availability for appointment id {} is confirmed, saving to database as id {}",
//                appointment.getId().getValue(), availabilityEvent.getEntity().getEventId());
//        Availability response = availabilityRepository.save(availabilityEvent.getEntity());
//        if(response == null) {
//            log.error("Failed to save availability entity for appointment id: {}", availabilityEvent.getEntity().getEventId());
//            throw new AvailabilityDomainException("Failed to save availability entity for appointment id: " +
//                    availabilityEvent.getEntity().getEventId());
//        }
//    }

}
