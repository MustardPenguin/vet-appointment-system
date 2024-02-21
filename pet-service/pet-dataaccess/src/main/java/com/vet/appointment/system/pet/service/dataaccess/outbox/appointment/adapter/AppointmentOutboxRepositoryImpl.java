package com.vet.appointment.system.pet.service.dataaccess.outbox.appointment.adapter;

import com.vet.appointment.system.dataaccess.outbox.appointment.entity.AppointmentOutboxEntity;
import com.vet.appointment.system.dataaccess.outbox.appointment.repository.AppointmentOutboxJpaRepository;
import com.vet.appointment.system.outbox.OutboxStatus;
import com.vet.appointment.system.pet.service.dataaccess.outbox.appointment.mapper.AppointmentDataAccessMapper;
import com.vet.appointment.system.pet.service.domain.dto.outbox.PetAppointmentOutboxMessage;
import com.vet.appointment.system.pet.service.domain.ports.output.repository.AppointmentOutboxRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public PetAppointmentOutboxMessage save(PetAppointmentOutboxMessage petAppointmentOutboxMessage) {
        AppointmentOutboxEntity appointmentOutboxEntity = appointmentOutboxJpaRepository
                .save(appointmentDataAccessMapper.petAppointmentOutboxMessageToOutboxEntity(petAppointmentOutboxMessage));
        return appointmentDataAccessMapper.outboxEntityToPetAppointmentOutboxMessage(appointmentOutboxEntity);
    }

    @Override
    public Optional<List<PetAppointmentOutboxMessage>> findByOutboxStatus(OutboxStatus outboxStatus) {
        return Optional.of(appointmentOutboxJpaRepository
                .findByOutboxStatus(outboxStatus).orElseGet(null)
                .stream().map(appointmentDataAccessMapper::outboxEntityToPetAppointmentOutboxMessage)
                .collect(Collectors.toList()));
    }

    @Override
    public void deleteAppointmentOutboxEntitiesByOutboxStatus(OutboxStatus outboxStatus) {
        appointmentOutboxJpaRepository.deleteAppointmentOutboxEntitiesByOutboxStatus(outboxStatus);
    }
}
