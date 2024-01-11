package com.vet.appointment.system.account.service.domain.ports.input;

import com.vet.appointment.system.account.service.domain.dto.create.CreateAccountCommand;

public interface AccountApplicationService {

    void createAccount(CreateAccountCommand createAccountCommand);
}
