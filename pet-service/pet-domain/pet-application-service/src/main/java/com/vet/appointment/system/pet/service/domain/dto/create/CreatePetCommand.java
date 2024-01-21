package com.vet.appointment.system.pet.service.domain.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

public class CreatePetCommand {

    private UUID ownerId;
    @NotBlank(message = "Pet name must not be blank!")
    private final String name;
    @NotBlank(message = "Species must not be blank!")
    private final String species;
    @DateTimeFormat
    @Past(message = "Birth date must be in the past!")
    private final LocalDate birthDate;

    public CreatePetCommand(String name, String species, LocalDate birthDate) {
        this.name = name;
        this.species = species;
        this.birthDate = birthDate;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
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
