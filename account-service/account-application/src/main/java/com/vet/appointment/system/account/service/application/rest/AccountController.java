package com.vet.appointment.system.account.service.application.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/test")
    public String test() {
        return "Hello world!";
    }
}
