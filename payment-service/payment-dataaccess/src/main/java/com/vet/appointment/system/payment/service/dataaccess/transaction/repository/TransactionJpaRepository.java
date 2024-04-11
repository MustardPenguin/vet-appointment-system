package com.vet.appointment.system.payment.service.dataaccess.transaction.repository;

import com.vet.appointment.system.payment.service.dataaccess.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, UUID> {

    Optional<TransactionEntity> findTransactionEntityById(UUID id);
}
