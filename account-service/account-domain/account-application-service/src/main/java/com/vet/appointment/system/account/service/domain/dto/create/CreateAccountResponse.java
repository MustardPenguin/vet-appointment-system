package com.vet.appointment.system.account.service.domain.dto.create;

import com.vet.appointment.system.domain.dto.ResponseMessage;

public class CreateAccountResponse extends ResponseMessage {

    public CreateAccountResponse(String message, int statusCode) {
        super(message, statusCode);
    }
}
