package com.vet.appointment.system.account.service.application.rest;

import com.vet.appointment.system.account.service.domain.dto.create.CreateAccountCommand;
import com.vet.appointment.system.account.service.domain.dto.create.CreateAccountResponse;
import com.vet.appointment.system.account.service.domain.ports.input.AccountApplicationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class AccountController {

    private final AccountApplicationService accountApplicationService;
    private final PasswordEncoder passwordEncoder;

    public AccountController(AccountApplicationService accountApplicationService, PasswordEncoder passwordEncoder) {
        this.accountApplicationService = accountApplicationService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/api/account")
    public ResponseEntity<CreateAccountResponse> registerAccountRequest(@RequestBody @Valid CreateAccountCommand createAccountCommand) {
        log.info("Creating account with email: {}", createAccountCommand.getEmail());
        createAccountCommand.setPassword(passwordEncoder.encode(createAccountCommand.getPassword()));
        CreateAccountResponse createAccountResponse = accountApplicationService.createAccount(createAccountCommand);

        return ResponseEntity.ok(createAccountResponse);
    }

    @GetMapping("/api/any")
    public String freeAccess() {
        return "I can be freely accessed!";
    }

    @GetMapping("/api/protected")
    public String authentication() {
        return "You can access this because you are authenticated!";
    }
}
