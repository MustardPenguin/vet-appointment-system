package com.vet.appointment.system.appointment.service.dataaccess.account.repository;

import com.vet.appointment.system.appointment.service.dataaccess.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {

    Optional<AccountEntity> findById(UUID id);
}
