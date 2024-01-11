package com.vet.appointment.system.account.service.dataaccess.account.repository;

import com.vet.appointment.system.account.service.dataaccess.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {
}
