package com.vet.appointment.system.pet.service.application.rest;

import com.vet.appointment.system.domain.dto.ResponseMessage;
import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetCommand;
import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetResponse;
import com.vet.appointment.system.pet.service.domain.dto.create.UpdatePetCommand;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import com.vet.appointment.system.pet.service.domain.ports.input.PetApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PutMapping("/api/pet")
    public ResponseEntity<CreatePetResponse> updatePetRequest(
            @RequestBody @Valid UpdatePetCommand updatePetCommand,
            @RequestHeader("accountId") UUID accountId) {
        CreatePetResponse createPetResponse = petApplicationService.updatePet(updatePetCommand, accountId);
        return ResponseEntity.ok(createPetResponse);
    }

    @DeleteMapping("/api/pet/{petId}")
    public ResponseEntity<CreatePetResponse> deletePetRequest(
            @PathVariable UUID petId,
            @RequestHeader("accountId") UUID accountId) {
        CreatePetResponse createPetResponse = petApplicationService.deletePet(petId, accountId);
        return ResponseEntity.ok(createPetResponse);
    }

    @GetMapping("/api/account/{accountId}/pet")
    public ResponseEntity<List<Pet>> getPetsByOwnerId(@PathVariable UUID accountId) {
        return ResponseEntity.ok(petApplicationService.findPetsByOwnerId(accountId));
    }
}