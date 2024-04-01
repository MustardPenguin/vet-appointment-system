package com.vet.appointment.system.application.debezium;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.appointment.system.application.webclient.WebClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class DebeziumConnectService {

    // application.yml values
    @Value("${debeziumHost}")
    private String DEBEZIUM_HOST;
    @Value("${spring.datasource.username}")
    private String POSTGRES_USER;
    @Value("${spring.datasource.password}")
    private String POSTGRES_PASSWORD;
    @Value("${databaseHost}")
    private String POSTGRES_HOST;

    private final WebClientService webClientService;
    private final ObjectMapper objectMapper;

    public DebeziumConnectService(WebClientService webClientService, ObjectMapper objectMapper) {
        this.webClientService = webClientService;
        this.objectMapper = objectMapper;
    }

    public void createDebeziumConnector(DebeziumConnectConfig debeziumConnectConfig) {
        // Create Debezium config for request body
        HashMap<String, String> config = debeziumConnectConfig.createHashmapConfig();
        config.put("database.hostname", POSTGRES_HOST);
        config.put("database.user", POSTGRES_USER);
        config.put("database.password", POSTGRES_PASSWORD);

        // Convert request body to JSON
        DebeziumConnectRequestBody debeziumConnectRequestBody = 
                new DebeziumConnectRequestBody(debeziumConnectConfig.getName(), config);
        String requestBody = writeValueAsString(debeziumConnectRequestBody);

        log.info("Request body: {}", requestBody);
        // Send request to Debezium connect
        String response = webClientService.postSynchronously(DEBEZIUM_HOST, requestBody, false);
        log.info("Response from Debezium connect: {}", response);
    }

    private String writeValueAsString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Error while converting object to JSON, Exception: ", e);
            return null;
        }
    }
}
