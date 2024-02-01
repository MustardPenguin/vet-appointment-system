package com.vet.appointment.system.appointment.service.domain.ports.output.repository;

import com.vet.appointment.system.appointment.service.domain.dto.message.PetModel;

public interface PetRepository {

    PetModel save(PetModel petModel);


}
