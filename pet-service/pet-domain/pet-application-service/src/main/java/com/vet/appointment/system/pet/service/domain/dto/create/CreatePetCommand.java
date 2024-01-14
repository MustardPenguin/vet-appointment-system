package com.vet.appointment.system.pet.service.domain.dto.create;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

public class CreatePetCommand {

    private final UUID ownerId;
    @NotBlank(message = "Pet name must not be blank!")
    private final String name;
    @NotBlank(message = "Species must not be blank!")
    private final String species;
    @DateTimeFormat
    private final LocalDate birthDate;

    public CreatePetCommand(UUID ownerId, String name, String species, LocalDate birthDate) {
        this.ownerId = ownerId;
        this.name = name;
        this.species = species;
        this.birthDate = birthDate;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
