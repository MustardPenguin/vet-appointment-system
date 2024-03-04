package com.vet.appointment.system.payment.service.dataaccess.balance.repository;

import com.vet.appointment.system.payment.service.dataaccess.balance.entity.BalanceEntity;
import com.vet.appointment.system.payment.service.domain.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BalanceJpaRepository extends JpaRepository<BalanceEntity, UUID> {

    Optional<BalanceEntity> findByAccountId(UUID accountId);
}
