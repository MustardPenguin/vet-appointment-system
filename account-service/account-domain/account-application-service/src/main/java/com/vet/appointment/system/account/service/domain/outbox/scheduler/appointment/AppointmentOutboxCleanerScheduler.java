package com.vet.appointment.system.account.service.domain.outbox.scheduler.appointment;

import com.vet.appointment.system.account.service.domain.ports.output.repository.AppointmentOutboxRepository;
import com.vet.appointment.system.outbox.OutboxScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppointmentOutboxCleanerScheduler implements OutboxScheduler {

    private final AppointmentOutboxRepository appointmentOutboxRepository;

    public AppointmentOutboxCleanerScheduler(AppointmentOutboxRepository appointmentOutboxRepository) {
        this.appointmentOutboxRepository = appointmentOutboxRepository;
    }

    @Override
    @Scheduled(cron = "@midnight")
    public void processOutboxMessage() {
        log.info("Cleaning account appointment outbox messages!");
        appointmentOutboxRepository.deleteAccountAppointmentOutboxMessage();
    }
}