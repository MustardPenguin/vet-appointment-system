package com.vet.appointment.system.account.service.domain.outbox.scheduler.appointment;

import com.vet.appointment.system.outbox.OutboxScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AppointmentOutboxCleanerScheduler implements OutboxScheduler {


    @Override
    @Scheduled(cron = "@midnight")
    public void processOutboxMessage() {

    }
}