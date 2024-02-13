package com.vet.appointment.system.appointment.service.messaging.listener.kafka;

import com.vet.appointment.system.appointment.service.domain.dto.message.AccountModel;
import com.vet.appointment.system.appointment.service.domain.ports.input.message.listener.AccountCreatedMessageListener;
import com.vet.appointment.system.kafka.avro.model.CreateAccountEventAvroModel;
import com.vet.appointment.system.kafka.consumer.KafkaConsumer;
import com.vet.appointment.system.kafka.producer.KafkaMessageHelper;
import com.vet.appointment.system.messaging.DebeziumOp;
import com.vet.appointment.system.messaging.event.AccountAppointmentEventPayload;
import debezium.account.appointment_outbox.Envelope;
import debezium.account.appointment_outbox.Value;
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
public class AccountCreatedEventKafkaListener implements KafkaConsumer<Envelope> {

    private final AccountCreatedMessageListener accountCreatedMessageListener;
    private final KafkaMessageHelper kafkaMessageHelper;

    public AccountCreatedEventKafkaListener(AccountCreatedMessageListener accountCreatedMessageListener,
                                            KafkaMessageHelper kafkaMessageHelper) {
        this.accountCreatedMessageListener = accountCreatedMessageListener;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    @KafkaListener(topics = CreateAccountEventTopicName, groupId = "${kafka-consumer-group-id.account-group-id}")
    public void receive(@Payload List<Envelope> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("Received kafka event message!");
        log.info("Messages: {}", messages);
        log.info("Key: {}", keys);
        log.info("Partitions: {}", partitions);
        log.info("Offsets: {}", offsets);

        messages.forEach(avroModel -> {
            if(avroModel.getBefore() == null && avroModel.getOp().equals(DebeziumOp.CREATE.getValue())) {
                Value accountEventAvroModel = avroModel.getAfter();
                AccountAppointmentEventPayload accountAppointmentEventPayload =
                        kafkaMessageHelper.getEventPayload(accountEventAvroModel.getPayload(), AccountAppointmentEventPayload.class);
                accountCreatedMessageListener.accountCreated(
                        new AccountModel(
                                accountAppointmentEventPayload.getId().toString(),
                                accountAppointmentEventPayload.getEmail(),
                                accountAppointmentEventPayload.getFirstName(),
                                accountAppointmentEventPayload.getLastName()));
            }
        });
//        messages.forEach(accountEventAvroModel ->
//                accountCreatedMessageListener.accountCreated(
//                        new AccountModel(
//                                accountEventAvroModel.getId(),
//                                accountEventAvroModel.getEmail(),
//                                accountEventAvroModel.getFirstName(),
//                                accountEventAvroModel.getLastName())));

    }
}
