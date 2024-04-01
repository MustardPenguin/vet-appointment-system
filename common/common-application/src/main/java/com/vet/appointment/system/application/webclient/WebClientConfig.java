package com.vet.appointment.system.application.webclient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    private final static int connectionTimeout = 5000;
    private final static int requestTimeout = 5000;
    private final static int readTimeout = 5000;

    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeout)
                .responseTimeout(Duration.ofMillis(requestTimeout))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(readTimeout)));

        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }
}
