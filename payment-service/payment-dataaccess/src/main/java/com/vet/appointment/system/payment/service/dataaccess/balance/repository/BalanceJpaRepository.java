package com.vet.appointment.system.payment.service.dataaccess.balance.repository;

import com.vet.appointment.system.payment.service.dataaccess.balance.entity.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BalanceJpaRepository extends JpaRepository<BalanceEntity, UUID> {
}
