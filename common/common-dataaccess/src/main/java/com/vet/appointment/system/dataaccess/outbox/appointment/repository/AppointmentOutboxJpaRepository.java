package com.vet.appointment.system.dataaccess.outbox.appointment.repository;

import com.vet.appointment.system.dataaccess.outbox.appointment.entity.AppointmentOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentOutboxJpaRepository extends JpaRepository<AppointmentOutboxEntity, UUID> {

    Optional<AppointmentOutboxEntity> findAppointmentOutboxEntityById(UUID id);
}
