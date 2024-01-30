package com.vet.appointment.system.appointment.service.domain.dto.message;

public class AccountModel {

    private String id;
    private String email;
    private String firstName;
    private String lastName;

    public AccountModel(String id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
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
