package com.vet.appointment.system.pet.service.dataaccess.outbox.appointment.adapter;

import com.vet.appointment.system.dataaccess.outbox.appointment.entity.AppointmentOutboxEntity;
import com.vet.appointment.system.dataaccess.outbox.appointment.repository.AppointmentOutboxJpaRepository;
import com.vet.appointment.system.pet.service.dataaccess.outbox.appointment.mapper.AppointmentDataAccessMapper;
import com.vet.appointment.system.pet.service.domain.outbox.model.PetAppointmentOutboxMessage;
import com.vet.appointment.system.pet.service.domain.ports.output.AppointmentOutboxRepository;
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
    public PetAppointmentOutboxMessage save(PetAppointmentOutboxMessage petAppointmentOutboxMessage) {
        AppointmentOutboxEntity appointmentOutboxEntity = appointmentOutboxJpaRepository
                .save(appointmentDataAccessMapper.petAppointmentOutboxMessageToOutboxEntity(petAppointmentOutboxMessage));
        return appointmentDataAccessMapper.outboxEntityToPetAppointmentOutboxMessage(appointmentOutboxEntity);
    }
}
