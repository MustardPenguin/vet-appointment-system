package com.vet.appointment.system.pet.service.domain.entity;

import com.vet.appointment.system.domain.entity.AggregateRoot;
import com.vet.appointment.system.domain.valueobject.PetId;
import com.vet.appointment.system.pet.service.domain.exception.PetDomainException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Pet extends AggregateRoot<PetId> {

    private final UUID ownerId;
    private final String name;
    private final String species;
    private final LocalDate birthDate;

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

    private Pet(Builder builder) {
        super.setId(new PetId(UUID.randomUUID()));
        ownerId = builder.ownerId;
        name = builder.name;
        species = builder.species;
        birthDate = builder.birthDate;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private UUID ownerId;
        private String name;
        private String species;
        private LocalDate birthDate;

        private Builder() {
        }

        public Builder ownerId(UUID val) {
            ownerId = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder species(String val) {
            species = val;
            return this;
        }

        public Builder birthDate(LocalDate val) {
            birthDate = val;
            return this;
        }

        public Pet build() {
            return new Pet(this);
        }
    }
}
