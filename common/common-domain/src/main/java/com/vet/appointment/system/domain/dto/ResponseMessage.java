package com.vet.appointment.system.domain.dto;

public class ResponseMessage {
    private final String message;
    private final int statusCode;


    public ResponseMessage(String message, int statusCode) {
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
