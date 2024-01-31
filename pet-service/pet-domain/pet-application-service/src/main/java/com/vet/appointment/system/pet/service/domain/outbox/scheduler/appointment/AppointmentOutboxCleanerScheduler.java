package com.vet.appointment.system.pet.service.domain.outbox.scheduler.appointment;

import com.vet.appointment.system.outbox.OutboxScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AppointmentOutboxCleanerScheduler implements OutboxScheduler {

    @Override
    @Scheduled(cron = "@midnight")
    public void processOutboxMessage() {

    }
}
