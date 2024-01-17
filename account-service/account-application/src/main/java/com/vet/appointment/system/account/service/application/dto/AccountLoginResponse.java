package com.vet.appointment.system.account.service.application.dto;

public class AccountLoginResponse {

    private final String message;
    private final String jwtToken;


    public AccountLoginResponse(String message, String jwtToken) {
        this.message = message;
        this.jwtToken = jwtToken;
    }

    public String getMessage() {
        return message;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
