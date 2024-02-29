package com.vet.appointment.system.payment.service.domain.dto.message;

import java.util.UUID;

public class AccountModel {

    private UUID id;
    private String email;

    public AccountModel(UUID id, String email) {
        this.id = id;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}
