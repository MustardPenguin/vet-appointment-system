package com.vet.appointment.system.pet.service.domain.dto.create;

import com.vet.appointment.system.domain.dto.ResponseMessage;

public class CreatePetResponse extends ResponseMessage {

    public CreatePetResponse(String message, int statusCode) {
        super(message, statusCode);
    }
}
