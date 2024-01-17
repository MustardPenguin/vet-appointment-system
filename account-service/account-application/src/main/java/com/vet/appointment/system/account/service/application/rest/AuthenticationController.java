package com.vet.appointment.system.account.service.application.rest;

import com.vet.appointment.system.account.service.application.dto.AccountLoginRequest;
import com.vet.appointment.system.account.service.application.dto.AccountLoginResponse;
import com.vet.appointment.system.account.service.application.security.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/api/authenticate")
    public AccountLoginResponse authenticate(@RequestBody AccountLoginRequest accountLoginRequest) {
        return authenticationService.authenticate(accountLoginRequest);
    }
}
