package com.vet.appointment.system.appointment.service.domain.ports.input.message.listener;

import com.vet.appointment.system.appointment.service.domain.dto.message.PetModel;

public interface PetCreatedMessageListener {

    void petCreated(PetModel petModel);
}
