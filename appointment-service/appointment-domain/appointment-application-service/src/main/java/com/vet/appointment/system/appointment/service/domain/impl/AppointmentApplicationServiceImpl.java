package com.vet.appointment.system.appointment.service.domain.impl;

import com.vet.appointment.system.appointment.service.domain.CreateAppointmentCommandHandler;
import com.vet.appointment.system.appointment.service.domain.dto.create.CreateAppointmentCommand;
import com.vet.appointment.system.appointment.service.domain.dto.message.AccountModel;
import com.vet.appointment.system.appointment.service.domain.dto.message.PetModel;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCreatedEvent;
import com.vet.appointment.system.appointment.service.domain.exception.AppointmentDomainException;
import com.vet.appointment.system.appointment.service.domain.ports.input.AppointmentApplicationService;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.AccountRepository;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class AppointmentApplicationServiceImpl implements AppointmentApplicationService {

    private final CreateAppointmentCommandHandler createAppointmentCommandHandler;
    private final AccountRepository accountRepository;
    private final PetRepository petRepository;

    public AppointmentApplicationServiceImpl(CreateAppointmentCommandHandler createAppointmentCommandHandler,
                                             AccountRepository accountRepository,
                                             PetRepository petRepository) {
        this.createAppointmentCommandHandler = createAppointmentCommandHandler;
        this.accountRepository = accountRepository;
        this.petRepository = petRepository;
    }


    @Override
    public void createAppointment(CreateAppointmentCommand createAppointmentCommand) {
        log.info("Creating appointment at the service layer for owner id {}", createAppointmentCommand.getOwnerId());

        Optional<AccountModel> accountModel = accountRepository.findById(createAppointmentCommand.getOwnerId());
        if(accountModel.isEmpty()) {
            log.warn("Could not find account with account id: {}", createAppointmentCommand.getOwnerId());
            throw new AppointmentDomainException("Could not find account with account id: " + createAppointmentCommand.getOwnerId());
        }
        Optional<PetModel> petModel = petRepository.findById(createAppointmentCommand.getPetId());
        if(petModel.isEmpty()) {
            log.warn("Could not find pet with pet id: {}", createAppointmentCommand.getPetId());
            throw new AppointmentDomainException("Could not find pet with pet id: " + createAppointmentCommand.getPetId());
        }

        AppointmentCreatedEvent appointmentCreatedEvent = createAppointmentCommandHandler
                .createAppointmentFromCommand(createAppointmentCommand, petModel.get());


    }
}
