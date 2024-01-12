package com.vet.appointment.system.account.service.domain.dto.create;

public class CreateAccountResponse {

    private final String message;
    private final int statusCode;

    public CreateAccountResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
