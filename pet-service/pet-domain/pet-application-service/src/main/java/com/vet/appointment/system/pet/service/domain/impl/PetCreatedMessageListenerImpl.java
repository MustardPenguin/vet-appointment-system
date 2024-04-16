package com.vet.appointment.system.pet.service.domain.impl;

import com.vet.appointment.system.pet.service.domain.dto.outbox.PetAppointmentOutboxMessage;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import com.vet.appointment.system.pet.service.domain.exception.PetDomainException;
import com.vet.appointment.system.pet.service.domain.outbox.scheduler.appointment.AppointmentOutboxHelper;
import com.vet.appointment.system.pet.service.domain.ports.input.message.listener.PetCreatedMessageListener;
import com.vet.appointment.system.pet.service.domain.ports.output.repository.AppointmentOutboxRepository;
import com.vet.appointment.system.pet.service.domain.ports.output.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class PetCreatedMessageListenerImpl implements PetCreatedMessageListener {

    private final AppointmentOutboxRepository appointmentOutboxRepository;
    private final PetRepository petRepository;

    public PetCreatedMessageListenerImpl(AppointmentOutboxRepository appointmentOutboxRepository,
                                         PetRepository petRepository) {
        this.appointmentOutboxRepository = appointmentOutboxRepository;
        this.petRepository = petRepository;
    }

    @Override
    @Transactional
    public void petCreated(Pet pet, UUID outboxId) {
        log.info("Successfully received propagation event for pet id: {} and owner id: {}", pet.getId().getValue(), pet.getOwnerId());

        Optional<PetAppointmentOutboxMessage> outboxMessage = appointmentOutboxRepository.getPetAppointmentOutboxMessage(outboxId);
        if(outboxMessage.isPresent()) {
            log.info("Outbox event from state propagation exists in this database, therefore this instance is the source of the event!");
            return;
        }
//        Pet existingPet = petRepository.getPetById(pet.getId().getValue());
//        if(existingPet != null) {
//            log.info("Pet with id: {} already exists in the database, either this instance is the source of message or it received a duplicate message!", pet.getId().getValue());
//            return;
//        }

        Pet response = petRepository.savePet(pet);
        if(response == null) {
            log.error("Could not save pet with pet id: {} and owner id: {}", pet.getId().getValue(), pet.getOwnerId());
            throw new PetDomainException("Could not save pet with pet id: " + pet.getId().getValue() + " and owner id: " + pet.getOwnerId());
        }
        log.info("Successfully saved pet with pet id: {} and owner id: {}", pet.getId().getValue(), pet.getOwnerId());
    }

    @Override
    @Transactional
    public void petDeleted(Pet pet, UUID outboxId) {
        log.info("Successfully received delete propagation event for pet id: {} and owner id: {}", pet.getId().getValue(), pet.getOwnerId());

        Optional<PetAppointmentOutboxMessage> outboxMessage = appointmentOutboxRepository.getPetAppointmentOutboxMessage(outboxId);
        if(outboxMessage.isPresent()) {
            log.info("Outbox event from state propagation exists in this database, therefore this instance is the source of the event!");
            return;
        }
        Pet existingPet = petRepository.getPetById(pet.getId().getValue());
        if(existingPet == null) {
            log.info("Pet with id: {} does not exist in the database for delete propagation!", pet.getId().getValue());
            return;
        }
        petRepository.deletePet(pet.getId().getValue());
        log.info("Successfully deleted pet with pet id: {} and owner id: {}", pet.getId().getValue(), pet.getOwnerId());
    }
}
