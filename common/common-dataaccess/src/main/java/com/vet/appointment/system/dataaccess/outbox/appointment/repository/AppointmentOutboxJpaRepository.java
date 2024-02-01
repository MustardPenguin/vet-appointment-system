package com.vet.appointment.system.dataaccess.outbox.appointment.repository;

import com.vet.appointment.system.dataaccess.outbox.appointment.entity.AppointmentOutboxEntity;
import com.vet.appointment.system.outbox.OutboxStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentOutboxJpaRepository extends JpaRepository<AppointmentOutboxEntity, UUID> {

    Optional<List<AppointmentOutboxEntity>> findByOutboxStatus(OutboxStatus outboxStatus);

    void deleteAppointmentOutboxEntitiesByOutboxStatus(OutboxStatus outboxStatus);
}
