package com.vet.appointment.system.appointment.service.dataaccess.pet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "pets")
public class PetEntity {

    @Id
    private UUID id;
    private UUID ownerId;
    private String name;
    private String species;
    private LocalDate birthDate;

    private PetEntity(Builder builder) {
        id = builder.id;
        ownerId = builder.ownerId;
        name = builder.name;
        species = builder.species;
        birthDate = builder.birthDate;
    }

    public PetEntity() {}

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

    public static Builder builder() {
        return new Builder();
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

        public PetEntity build() {
            return new PetEntity(this);
        }
    }
}
