package com.vet.appointment.system.account.service.application.security;

import com.vet.appointment.system.account.service.application.dto.AccountLoginRequest;
import com.vet.appointment.system.account.service.application.dto.AccountLoginResponse;

public interface AuthenticationService {

    AccountLoginResponse authenticate(AccountLoginRequest accountLoginRequest);
}
