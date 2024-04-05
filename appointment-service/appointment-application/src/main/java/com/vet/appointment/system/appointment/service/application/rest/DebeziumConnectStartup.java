package com.vet.appointment.system.appointment.service.application.rest;

import com.vet.appointment.system.application.debezium.DebeziumConnectConfig;
import com.vet.appointment.system.application.debezium.DebeziumConnectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DebeziumConnectStartup implements ApplicationRunner {

    @Value("${applicationDeploymentName}")
    private String APPLICATION_DEPLOYMENT_NAME;

    private final DebeziumConnectService debeziumConnectService;

    public DebeziumConnectStartup(DebeziumConnectService debeziumConnectService) {
        this.debeziumConnectService = debeziumConnectService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        DebeziumConnectConfig appointmentCreatedConfig = DebeziumConnectConfig.builder()
                .tableIncludeList("appointment.appointment_outbox")
                .slotName("appointment_created_outbox_slot")
                .topicPrefix("appointment_created")
                .name(APPLICATION_DEPLOYMENT_NAME + "-appointment-created-connector")
                .build();

        DebeziumConnectConfig appointmentAvailabilityConfig = DebeziumConnectConfig.builder()
                .tableIncludeList("appointment.availability_outbox")
                .slotName("appointment_availability_outbox_slot")
                .topicPrefix("availability_request")
                .name(APPLICATION_DEPLOYMENT_NAME + "-availability-connector")
                .build();


        DebeziumConnectConfig appointmentPaymentConfig = DebeziumConnectConfig.builder()
                .tableIncludeList("appointment.payment_outbox")
                .slotName("appointment_payment_outbox_slot")
                .topicPrefix("payment_request")
                .name(APPLICATION_DEPLOYMENT_NAME + "-payment-connector")
                .build();

        debeziumConnectService.createDebeziumConnector(appointmentCreatedConfig);
        debeziumConnectService.createDebeziumConnector(appointmentAvailabilityConfig);
        debeziumConnectService.createDebeziumConnector(appointmentPaymentConfig);
    }
}
