package com.vet.appointment.system.payment.service.messaging.listener.kafka;

import account_created.account.appointment_outbox.Envelope;
import account_created.account.appointment_outbox.Value;
import com.vet.appointment.system.kafka.consumer.KafkaConsumer;
import com.vet.appointment.system.kafka.producer.KafkaMessageHelper;
import com.vet.appointment.system.messaging.DebeziumOp;
import com.vet.appointment.system.messaging.event.AccountCreatedEventPayload;
import com.vet.appointment.system.payment.service.domain.dto.message.AccountModel;
import com.vet.appointment.system.payment.service.domain.ports.input.message.listener.AccountCreatedMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

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
    @KafkaListener(topics = "${kafka-consumer-topic-name.account-created-event}",
        groupId = "account-created-listener-#{T(java.util.UUID).randomUUID().toString()}")
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
                AccountCreatedEventPayload accountCreatedEventPayload =
                        kafkaMessageHelper.getEventPayload(accountEventAvroModel.getPayload(), AccountCreatedEventPayload.class);

                accountCreatedMessageListener.accountCreated(new AccountModel(
                        accountCreatedEventPayload.getId(),
                        accountCreatedEventPayload.getEmail()
                ));
            }
        });
    }
}
