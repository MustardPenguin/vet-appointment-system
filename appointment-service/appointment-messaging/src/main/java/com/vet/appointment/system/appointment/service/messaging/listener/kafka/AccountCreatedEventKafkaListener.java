package com.vet.appointment.system.appointment.service.messaging.listener.kafka;

import com.vet.appointment.system.appointment.service.domain.dto.message.AccountModel;
import com.vet.appointment.system.appointment.service.domain.ports.input.message.listener.AccountCreatedMessageListener;
import com.vet.appointment.system.kafka.avro.model.CreateAccountEventAvroModel;
import com.vet.appointment.system.kafka.consumer.KafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.vet.appointment.system.appointment.service.domain.config.AppointmentServiceDataConfig.CreateAccountEventTopicName;

@Slf4j
@Component
public class AccountCreatedEventKafkaListener implements KafkaConsumer<CreateAccountEventAvroModel> {

    private final AccountCreatedMessageListener accountCreatedMessageListener;

    public AccountCreatedEventKafkaListener(AccountCreatedMessageListener accountCreatedMessageListener) {
        this.accountCreatedMessageListener = accountCreatedMessageListener;
    }

    // TODO Add headers for arguments keys and partitions
    @Override
    @KafkaListener(topics = CreateAccountEventTopicName, groupId = "1")
    public void receive(@Payload List<CreateAccountEventAvroModel> messages,
                        List<String> keys,
                        List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("Received kafka event message!");
        log.info("Messages: {}", messages);
        log.info("Key: {}", keys);
        log.info("Partitions: {}", partitions);
        log.info("Offsets: {}", offsets);
        messages.forEach(accountEventAvroModel ->
                accountCreatedMessageListener.accountCreated(
                        new AccountModel(
                                accountEventAvroModel.getId(),
                                accountEventAvroModel.getEmail(),
                                accountEventAvroModel.getFirstName(),
                                accountEventAvroModel.getLastName())));

    }
}
