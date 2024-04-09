package com.vet.appointment.system.payment.service.application.rest;

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
        DebeziumConnectConfig availabilityResponseConfig = DebeziumConnectConfig.builder()
                .tableIncludeList("payment.appointment_outbox")
                .slotName("payment_appointment_outbox_slot")
                .topicPrefix("payment_response")
                .name(APPLICATION_DEPLOYMENT_NAME + "-appointment-connector")
                .build();

        debeziumConnectService.createDebeziumConnector(availabilityResponseConfig);
    }
}
