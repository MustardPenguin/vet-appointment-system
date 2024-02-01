package com.vet.appointment.system.appointment.service.domain.dto.message;

import java.time.LocalDate;
import java.util.UUID;

public class PetModel {

    private UUID id;
    private UUID ownerId;
    private String name;
    private String species;
    private LocalDate birthDate;

    private PetModel(Builder builder) {
        id = builder.id;
        ownerId = builder.ownerId;
        name = builder.name;
        species = builder.species;
        birthDate = builder.birthDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getId() {
        return id;
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


    public static final class Builder {
        private UUID id;
        private UUID ownerId;
        private String name;
        private String species;
        private LocalDate birthDate;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
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

        public PetModel build() {
            return new PetModel(this);
        }
    }
}
