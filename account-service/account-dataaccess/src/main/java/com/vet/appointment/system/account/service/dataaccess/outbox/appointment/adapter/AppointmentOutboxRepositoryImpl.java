package com.vet.appointment.system.account.service.dataaccess.outbox.appointment.adapter;

import com.vet.appointment.system.account.service.dataaccess.outbox.appointment.mapper.AppointmentDataAccessMapper;
import com.vet.appointment.system.account.service.domain.dto.outbox.AccountAppointmentOutboxMessage;
import com.vet.appointment.system.account.service.domain.ports.output.repository.AppointmentOutboxRepository;
import com.vet.appointment.system.dataaccess.outbox.appointment.entity.AppointmentOutboxEntity;
import com.vet.appointment.system.dataaccess.outbox.appointment.repository.AppointmentOutboxJpaRepository;
import com.vet.appointment.system.outbox.OutboxStatus;
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
    public AccountAppointmentOutboxMessage save(AccountAppointmentOutboxMessage accountAppointmentOutboxMessage) {
        AppointmentOutboxEntity appointmentOutboxEntity = appointmentOutboxJpaRepository.save(
            appointmentDataAccessMapper.accountAppointmentOutboxMessageToOutboxEntity(accountAppointmentOutboxMessage));
        return appointmentDataAccessMapper.outboxEntityToAccountAppointmentOutboxMessage(appointmentOutboxEntity);
    }

    @Override
    public Optional<List<AccountAppointmentOutboxMessage>> findByOutboxStatus(OutboxStatus outboxStatus) {
        return Optional.of(appointmentOutboxJpaRepository.findByOutboxStatus(OutboxStatus.STARTED).orElseGet(null)
                .stream().map(appointmentDataAccessMapper::outboxEntityToAccountAppointmentOutboxMessage)
                .collect(Collectors.toList()));
    }

    @Override
    public void deleteAppointmentOutboxEntitiesByOutboxStatus(OutboxStatus outboxStatus) {
        appointmentOutboxJpaRepository.deleteAppointmentOutboxEntitiesByOutboxStatus(outboxStatus);
    }
}
