package com.vet.appointment.system.pet.service.application.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {

    @GetMapping("/api/test")
    public String test() {
        return "Hello world!";
    }


}
