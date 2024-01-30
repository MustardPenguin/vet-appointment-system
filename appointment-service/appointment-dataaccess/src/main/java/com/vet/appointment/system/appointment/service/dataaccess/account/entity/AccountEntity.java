package com.vet.appointment.system.appointment.service.dataaccess.account.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.UUID;

@Entity
@Table(schema = "appointment", name = "accounts")
public class AccountEntity {

    @Id
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;

    public AccountEntity() {}

    public AccountEntity(UUID id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
