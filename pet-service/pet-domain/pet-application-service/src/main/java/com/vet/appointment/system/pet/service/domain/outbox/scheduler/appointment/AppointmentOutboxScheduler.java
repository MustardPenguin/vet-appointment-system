package com.vet.appointment.system.pet.service.domain.outbox.scheduler.appointment;

import com.vet.appointment.system.outbox.OutboxScheduler;
import com.vet.appointment.system.outbox.OutboxStatus;
import com.vet.appointment.system.pet.service.domain.outbox.model.PetAppointmentOutboxMessage;
import com.vet.appointment.system.pet.service.domain.ports.output.message.publisher.PetCreatedMessagePublisher;
import com.vet.appointment.system.pet.service.domain.ports.output.repository.AppointmentOutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class AppointmentOutboxScheduler implements OutboxScheduler {

    private final AppointmentOutboxRepository appointmentOutboxRepository;
    private final PetCreatedMessagePublisher petCreatedMessagePublisher;
    private final AppointmentOutboxHelper appointmentOutboxHelper;

    public AppointmentOutboxScheduler(AppointmentOutboxRepository appointmentOutboxRepository,
                                      PetCreatedMessagePublisher petCreatedMessagePublisher,
                                      AppointmentOutboxHelper appointmentOutboxHelper) {
        this.appointmentOutboxRepository = appointmentOutboxRepository;
        this.petCreatedMessagePublisher = petCreatedMessagePublisher;
        this.appointmentOutboxHelper = appointmentOutboxHelper;
    }

    @Transactional
    @Override
    @Scheduled(fixedRateString = "${pet-service.outbox-scheduler-rate}",
    initialDelayString = "${pet-service.outbox-scheduler-delay}")
    public void processOutboxMessage() {
        Optional<List<PetAppointmentOutboxMessage>> response = appointmentOutboxRepository.findByOutboxStatus(OutboxStatus.STARTED);

        if(response.isPresent() && response.get().size() > 0) {
            List<PetAppointmentOutboxMessage> messages = response.get();
            messages.forEach(message ->
                    petCreatedMessagePublisher.publish(message, this::updateOutboxStatus));
        }
    }

    public void updateOutboxStatus(PetAppointmentOutboxMessage petAppointmentOutboxMessage,
                                   OutboxStatus outboxStatus) {
        petAppointmentOutboxMessage.setOutboxStatus(outboxStatus);
        appointmentOutboxHelper.save(petAppointmentOutboxMessage);
        log.info("Updated outbox message status of id: {} to {}",
                petAppointmentOutboxMessage.getId(), outboxStatus);
    }
}
