package com.vet.appointment.system.account.service.application.dto;

public class AccountLoginRequest {

    private String email;
    private String password;

    public AccountLoginRequest() {}

    public AccountLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
