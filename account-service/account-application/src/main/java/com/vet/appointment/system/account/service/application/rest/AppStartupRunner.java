package com.vet.appointment.system.account.service.application.rest;

import com.vet.appointment.system.application.debezium.DebeziumConnectConfig;
import com.vet.appointment.system.application.debezium.DebeziumConnectService;
import com.vet.appointment.system.application.webclient.WebClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class AppStartupRunner implements ApplicationRunner {

    @Value("${applicationDeploymentName}")
    private String APPLICATION_DEPLOYMENT_NAME;

    private final DebeziumConnectService debeziumConnectService;

    public AppStartupRunner(DebeziumConnectService debeziumConnectService) {
        this.debeziumConnectService = debeziumConnectService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        DebeziumConnectConfig debeziumConnectConfig = DebeziumConnectConfig.builder()
                .tableIncludeList("account.appointment_outbox")
                .slotName("account_appointment_outbox_slot")
                .topicPrefix("account_created")
                .name(APPLICATION_DEPLOYMENT_NAME + "-appointment-connector")
                .build();

        debeziumConnectService.createDebeziumConnector(debeziumConnectConfig);
    }
}
