package com.vet.appointment.system.pet.service.application.rest;

import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetCommand;
import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetResponse;
import com.vet.appointment.system.pet.service.domain.ports.input.PetApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PetController {

    private final PetApplicationService petApplicationService;

    public PetController(PetApplicationService petApplicationService) {
        this.petApplicationService = petApplicationService;
    }

    @GetMapping("/api/test")
    public String test() {
        return "Hello world!";
    }

    @PostMapping("/api/pet")
    public ResponseEntity<CreatePetResponse> createPetRequest(
            @RequestBody @Valid CreatePetCommand createPetCommand,
            @RequestHeader("accountId") UUID accountId) {

        createPetCommand.setOwnerId(accountId);
        CreatePetResponse createPetResponse = petApplicationService.createPet(createPetCommand);

        return ResponseEntity.ok(createPetResponse);
    }
}
