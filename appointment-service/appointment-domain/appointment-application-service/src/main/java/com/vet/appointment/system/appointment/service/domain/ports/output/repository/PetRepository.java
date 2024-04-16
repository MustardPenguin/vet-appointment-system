package com.vet.appointment.system.appointment.service.domain.ports.output.repository;

import com.vet.appointment.system.appointment.service.domain.dto.message.PetModel;

import java.util.Optional;
import java.util.UUID;

public interface PetRepository {

    PetModel save(PetModel petModel);

    Optional<PetModel> findById(UUID id);

    void deleteById(UUID id);
}
