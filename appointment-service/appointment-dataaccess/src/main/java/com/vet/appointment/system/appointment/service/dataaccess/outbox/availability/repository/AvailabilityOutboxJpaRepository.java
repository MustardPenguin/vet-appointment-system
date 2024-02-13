package com.vet.appointment.system.appointment.service.dataaccess.outbox.availability.repository;

import com.vet.appointment.system.appointment.service.dataaccess.outbox.availability.entity.AvailabilityOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AvailabilityOutboxJpaRepository extends JpaRepository<AvailabilityOutboxEntity, UUID> {
}