package com.vet.appointment.system.appointment.service.domain.ports.output.repository;

import com.vet.appointment.system.appointment.service.domain.dto.message.AccountModel;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    AccountModel save(AccountModel accountModel);

    Optional<AccountModel> findById(UUID id);
}
