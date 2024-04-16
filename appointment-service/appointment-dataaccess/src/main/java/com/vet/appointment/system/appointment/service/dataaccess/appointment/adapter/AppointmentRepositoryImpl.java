package com.vet.appointment.system.appointment.service.dataaccess.appointment.adapter;

import com.vet.appointment.system.appointment.service.dataaccess.appointment.entity.AppointmentEntity;
import com.vet.appointment.system.appointment.service.dataaccess.appointment.mapper.AppointmentDataAccessMapper;
import com.vet.appointment.system.appointment.service.dataaccess.appointment.repository.AppointmentJpaRepository;
import com.vet.appointment.system.appointment.service.domain.dto.message.AccountModel;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.AppointmentRepository;
import com.vet.appointment.system.domain.valueobject.AccountId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final AppointmentJpaRepository appointmentJpaRepository;
    private final AppointmentDataAccessMapper appointmentDataAccessMapper;

    public AppointmentRepositoryImpl(AppointmentJpaRepository appointmentJpaRepository,
                                     AppointmentDataAccessMapper appointmentDataAccessMapper) {
        this.appointmentJpaRepository = appointmentJpaRepository;
        this.appointmentDataAccessMapper = appointmentDataAccessMapper;
    }

    @Override
    public Appointment save(Appointment appointment) {
        AppointmentEntity appointmentEntity = appointmentJpaRepository
                .save(appointmentDataAccessMapper.appointmentToAppointmentEntity(appointment));
        return appointmentDataAccessMapper.appointmentEntityToAppointment(appointmentEntity);
    }

    @Override
    public Optional<Appointment> findById(UUID accountId) {
        return appointmentJpaRepository.findById(accountId).map(appointmentDataAccessMapper::appointmentEntityToAppointment);
    }

    @Override
    public List<Appointment> findAppointmentsByOwnerId(UUID accountId) {
        return appointmentJpaRepository.findAppointmentEntitiesByOwnerId(accountId)
                .stream().map(appointmentDataAccessMapper::appointmentEntityToAppointment).collect(Collectors.toList());
    }
}
