package com.vet.appointment.system.account.service.domain.ports.input;

import com.vet.appointment.system.account.service.domain.dto.create.CreateAccountCommand;
import com.vet.appointment.system.account.service.domain.dto.create.CreateAccountResponse;

public interface AccountApplicationService {

    CreateAccountResponse createAccount(CreateAccountCommand createAccountCommand);
}
