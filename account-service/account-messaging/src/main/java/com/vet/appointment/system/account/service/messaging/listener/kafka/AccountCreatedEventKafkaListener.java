package com.vet.appointment.system.account.service.messaging.listener.kafka;

import account_created.account.appointment_outbox.Envelope;
import account_created.account.appointment_outbox.Value;
import com.vet.appointment.system.account.service.domain.ports.input.message.listener.AccountCreatedMessageListener;
import com.vet.appointment.system.account.service.messaging.mapper.AccountMessagingDataMapper;
import com.vet.appointment.system.kafka.consumer.KafkaConsumer;
import com.vet.appointment.system.kafka.producer.KafkaMessageHelper;
import com.vet.appointment.system.messaging.DebeziumOp;
import com.vet.appointment.system.messaging.event.AccountCreatedEventPayload;
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
    private final AccountMessagingDataMapper accountMessagingDataMapper;
    private final KafkaMessageHelper kafkaMessageHelper;

    public AccountCreatedEventKafkaListener(AccountCreatedMessageListener accountCreatedMessageListener,
                                            AccountMessagingDataMapper accountMessagingDataMapper,
                                            KafkaMessageHelper kafkaMessageHelper) {
        this.accountCreatedMessageListener = accountCreatedMessageListener;
        this.accountMessagingDataMapper = accountMessagingDataMapper;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    @KafkaListener(topics = "${kafka-consumer-topic-name.account-created-event}",
            groupId = "${applicationDeploymentName}-account-created-listener")
    public void receive(@Payload List<Envelope> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("Received account created event!");

        messages.forEach(message -> {
            if(message.getBefore() == null && message.getOp().equals(DebeziumOp.CREATE.getValue())) {
                Value avroModel = message.getAfter();
                AccountCreatedEventPayload accountCreatedEventPayload =
                        kafkaMessageHelper.getEventPayload(avroModel.getPayload(), AccountCreatedEventPayload.class);
                accountCreatedMessageListener
                        .accountCreated(accountMessagingDataMapper.accountCreatedEventPayloadToAccount(accountCreatedEventPayload));
            }
        });
    }
}
