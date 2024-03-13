package com.vet.appointment.system.appointment.service.dataaccess.outbox.availability.repository;

import com.vet.appointment.system.appointment.service.dataaccess.outbox.availability.entity.AvailabilityOutboxEntity;
import com.vet.appointment.system.saga.SagaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AvailabilityOutboxJpaRepository extends JpaRepository<AvailabilityOutboxEntity, UUID> {

//    Optional<AvailabilityOutboxEntity> findAvailabilityOutboxEntityBySagaTypeAndSagaIdAndSagaStatus(String sagaType, UUID sagaId, SagaStatus sagaStatus);
    Optional<AvailabilityOutboxEntity> findAvailabilityOutboxEntityBySagaTypeAndSagaIdAndSagaStatusIn(String sagaType, UUID sagaId, Collection<SagaStatus> sagaStatuses);
}
