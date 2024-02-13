package com.vet.appointment.system.appointment.service.domain.config;

public class AppointmentServiceDataConfig {
    private AppointmentServiceDataConfig() {}

    public final static String CreateAccountEventTopicName = "debezium.account.appointment_outbox";
    public final static String CreatePetEventTopicName = "pet-created-event";
}
