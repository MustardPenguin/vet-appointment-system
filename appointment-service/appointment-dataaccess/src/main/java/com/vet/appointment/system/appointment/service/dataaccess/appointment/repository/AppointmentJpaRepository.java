package com.vet.appointment.system.appointment.service.dataaccess.appointment.repository;

import com.vet.appointment.system.appointment.service.dataaccess.account.entity.AccountEntity;
import com.vet.appointment.system.appointment.service.dataaccess.appointment.entity.AppointmentEntity;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, UUID> {

    List<AppointmentEntity> findAppointmentEntitiesByOwnerId(UUID accountId);
}
