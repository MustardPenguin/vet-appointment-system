package com.vet.appointment.system.appointment.service.dataaccess.outbox.appointment.adapter;

import com.vet.appointment.system.appointment.service.dataaccess.outbox.appointment.mapper.AppointmentOutboxDataAccessMapper;
import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentOutboxMessage;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.outbox.AppointmentOutboxRepository;
import com.vet.appointment.system.dataaccess.outbox.appointment.entity.AppointmentOutboxEntity;
import com.vet.appointment.system.dataaccess.outbox.appointment.repository.AppointmentOutboxJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AppointmentOutboxRepositoryImpl implements AppointmentOutboxRepository {

    private final AppointmentOutboxDataAccessMapper appointmentOutboxDataAccessMapper;
    private final AppointmentOutboxJpaRepository appointmentOutboxJpaRepository;

    public AppointmentOutboxRepositoryImpl(AppointmentOutboxDataAccessMapper appointmentOutboxDataAccessMapper,
                                           AppointmentOutboxJpaRepository appointmentOutboxJpaRepository) {
        this.appointmentOutboxDataAccessMapper = appointmentOutboxDataAccessMapper;
        this.appointmentOutboxJpaRepository = appointmentOutboxJpaRepository;
    }

    @Override
    public AppointmentOutboxMessage save(AppointmentOutboxMessage appointmentOutboxMessage) {
        AppointmentOutboxEntity appointmentOutboxEntity = appointmentOutboxJpaRepository.save(appointmentOutboxDataAccessMapper
                        .appointmentOutboxMessageToOutboxEntity(appointmentOutboxMessage));
        return appointmentOutboxDataAccessMapper.appointmentOutboxEntityToOutboxMessage(appointmentOutboxEntity);
    }

    @Override
    public Optional<AppointmentOutboxMessage> findById(UUID id) {
        try {
            return appointmentOutboxJpaRepository.findById(id).map(appointmentOutboxDataAccessMapper::appointmentOutboxEntityToOutboxMessage);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
