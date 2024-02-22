package com.vet.appointment.system.availability.service.dataaccess.outbox.appointment.adapter;

import com.vet.appointment.system.availability.service.dataaccess.outbox.appointment.entity.AppointmentOutboxEntity;
import com.vet.appointment.system.availability.service.dataaccess.outbox.appointment.mapper.AppointmentDataAccessMapper;
import com.vet.appointment.system.availability.service.dataaccess.outbox.appointment.repository.AppointmentOutboxJpaRepository;
import com.vet.appointment.system.availability.service.domain.dto.outbox.AvailabilityAppointmentOutboxMessage;
import com.vet.appointment.system.availability.service.domain.ports.output.repository.AppointmentOutboxRepository;
import org.springframework.stereotype.Component;

@Component
public class AppointmentOutboxRepositoryImpl implements AppointmentOutboxRepository {

    private final AppointmentOutboxJpaRepository appointmentOutboxJpaRepository;
    private final AppointmentDataAccessMapper appointmentDataAccessMapper;

    public AppointmentOutboxRepositoryImpl(AppointmentOutboxJpaRepository appointmentOutboxJpaRepository,
                                           AppointmentDataAccessMapper appointmentDataAccessMapper) {
        this.appointmentOutboxJpaRepository = appointmentOutboxJpaRepository;
        this.appointmentDataAccessMapper = appointmentDataAccessMapper;
    }

    @Override
    public AvailabilityAppointmentOutboxMessage save(AvailabilityAppointmentOutboxMessage availabilityAppointmentOutboxMessage) {
        AppointmentOutboxEntity appointmentOutboxEntity = appointmentOutboxJpaRepository
                .save(appointmentDataAccessMapper.appointmentOutboxMessageToOutboxEntity(availabilityAppointmentOutboxMessage));
        return appointmentDataAccessMapper.outboxEntityToAppointmentOutboxMessage(appointmentOutboxEntity);
    }
}
