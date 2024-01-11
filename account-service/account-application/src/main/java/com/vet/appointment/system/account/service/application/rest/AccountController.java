package com.vet.appointment.system.account.service.application.rest;

import com.vet.appointment.system.account.service.domain.dto.create.CreateAccountCommand;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/api/test")
    public String test() {
        return "Hello world!";
    }

    @PostMapping("/api/account")
    public String registerAccount(@RequestBody @Valid CreateAccountCommand createAccountCommand) {
        System.out.println(createAccountCommand.getEmail());
        System.out.println(createAccountCommand.getFirstName());

        return "Test";
    }
}
