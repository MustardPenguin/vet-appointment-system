package com.vet.appointment.system.appointment.service.dataaccess.outbox.availability.adapter;

import com.vet.appointment.system.appointment.service.dataaccess.outbox.availability.entity.AvailabilityOutboxEntity;
import com.vet.appointment.system.appointment.service.dataaccess.outbox.availability.mapper.AvailabilityDataAccessMapper;
import com.vet.appointment.system.appointment.service.dataaccess.outbox.availability.repository.AvailabilityOutboxJpaRepository;
import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentAvailabilityOutboxMessage;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.AvailabilityOutboxRepository;
import com.vet.appointment.system.saga.SagaStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AvailabilityOutboxRepositoryImpl implements AvailabilityOutboxRepository {

    private final AvailabilityOutboxJpaRepository availabilityOutboxJpaRepository;
    private final AvailabilityDataAccessMapper availabilityDataAccessMapper;

    public AvailabilityOutboxRepositoryImpl(AvailabilityOutboxJpaRepository availabilityOutboxJpaRepository,
                                            AvailabilityDataAccessMapper availabilityDataAccessMapper) {
        this.availabilityOutboxJpaRepository = availabilityOutboxJpaRepository;
        this.availabilityDataAccessMapper = availabilityDataAccessMapper;
    }

    @Override
    public AppointmentAvailabilityOutboxMessage save(AppointmentAvailabilityOutboxMessage appointmentAvailabilityOutboxMessage) {
        AvailabilityOutboxEntity availabilityOutbox = availabilityOutboxJpaRepository
                .save(availabilityDataAccessMapper.availabilityOutboxMessageToOutboxEntity(appointmentAvailabilityOutboxMessage));
        return availabilityDataAccessMapper.availabilityOutboxEntityToOutboxMessage(availabilityOutbox);
    }

    @Override
    public Optional<AppointmentAvailabilityOutboxMessage> findBySagaIdAndSagaStatus(String sagaType, UUID sagaId, SagaStatus sagaStatus) {
        return availabilityOutboxJpaRepository.findAvailabilityOutboxEntityBySagaTypeAndSagaIdAndSagaStatus(sagaType, sagaId, sagaStatus)
                .map(availabilityDataAccessMapper::availabilityOutboxEntityToOutboxMessage);
    }
}
