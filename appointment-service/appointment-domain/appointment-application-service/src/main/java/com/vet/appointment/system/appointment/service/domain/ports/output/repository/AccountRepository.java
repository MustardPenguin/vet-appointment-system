package com.vet.appointment.system.appointment.service.domain.ports.output.repository;

import com.vet.appointment.system.appointment.service.domain.dto.message.AccountModel;

public interface AccountRepository {

    AccountModel save(AccountModel accountModel);
}
