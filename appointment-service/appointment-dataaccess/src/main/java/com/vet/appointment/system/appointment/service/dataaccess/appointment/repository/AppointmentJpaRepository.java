package com.vet.appointment.system.appointment.service.dataaccess.appointment.repository;

import com.vet.appointment.system.appointment.service.dataaccess.account.entity.AccountEntity;
import com.vet.appointment.system.appointment.service.dataaccess.appointment.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, UUID> {

}
