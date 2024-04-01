package com.vet.appointment.system.application.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class WebClientService {

    private final WebClient webClient;

    public WebClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String postSynchronously(String url, String body, Boolean exitIfException) {
        log.info("Sending POST request to: {}", url);
        String response = "";
        try {
            response = webClient.post()
                    .uri(url)
                    .bodyValue(body)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            log.error("Error while sending POST request to: {}, Exception: ", url, e);
            if(exitIfException) {
                log.info("exitIfException set to true, exiting application...");
                System.exit(1);
            }
        } finally {
            log.info("Response: {}", response);
        }
        return response;
    }
}
