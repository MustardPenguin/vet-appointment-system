package com.vet.appointment.system.appointment.service.domain.impl.message.listener;

import com.vet.appointment.system.appointment.service.domain.dto.message.PetModel;
import com.vet.appointment.system.appointment.service.domain.exception.AppointmentDomainException;
import com.vet.appointment.system.appointment.service.domain.ports.input.message.listener.PetCreatedMessageListener;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PetCreatedMessageListenerImpl implements PetCreatedMessageListener {

    private final PetRepository petRepository;

    public PetCreatedMessageListenerImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void petCreated(PetModel petModel) {
        PetModel response = petRepository.save(petModel);
        if(response == null) {
            log.error("Unable to save pet entity of id: {} to appointment database!", petModel.getId());
            throw new AppointmentDomainException("Unable to save pet entity of id: " + petModel.getId()
                                            + " to appointment database!");
        }
        log.info("Successfully saved pet entity of id: {} to appointment database.", petModel.getId());
    }
}
